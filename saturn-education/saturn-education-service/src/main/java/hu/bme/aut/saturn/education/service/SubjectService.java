package hu.bme.aut.saturn.education.service;

import hu.bme.aut.saturn.education.managementclient.v1.UserApi;
import hu.bme.aut.saturn.education.mapper.SubjectMapper;
import hu.bme.aut.saturn.education.persistence.entity.Semester;
import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.enums.PeriodOfYear;
import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import hu.bme.aut.saturn.education.persistence.relation.SubjectDeputy;
import hu.bme.aut.saturn.education.persistence.relation.SubjectRegistration;
import hu.bme.aut.saturn.education.persistence.repository.SubjectDeputyRepository;
import hu.bme.aut.saturn.education.persistence.repository.SubjectRegistrationRepository;
import hu.bme.aut.saturn.education.persistence.repository.SubjectRepository;
import hu.bme.aut.saturn.education.service.v1.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectDeputyRepository subjectDeputyRepository;

    private final SubjectMapper subjectMapper;

    private final SemesterService semesterService;

    @Lazy
    private final CourseService courseService;

    private final UserApi userApi;

    private final SubjectRegistrationRepository subjectRegistrationRepository;

    public void createSubject(CreateSubjectRequestDto createSubjectRequestDto) {
        Subject subject = subjectMapper.toEntity(createSubjectRequestDto);

        createSubjectDeputies(createSubjectRequestDto.getSubjectDeputies(), subjectRepository.save(subject));
    }

    public Subject getSubject(UUID subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(NoSuchElementException::new);
    }

    private void createSubjectDeputies(List<UUID> subjectDeputyUuids, Subject subject) {
        subjectDeputyUuids.forEach(uuid -> {
            SubjectDeputy subjectDeputy = new SubjectDeputy();
            subjectDeputy.setSubject(subject);
            subjectDeputy.setTeacherUuid(uuid);

            subject.addSubjectDeputy(subjectDeputyRepository.save(subjectDeputy));
        });
    }

    public List<SubjectOfTeacherDto> getSubjectsOfTeacher(UUID currentTeacherUuid) {
        return subjectMapper.toTeacherDtos(subjectRepository.findAllByTeacherUuid(currentTeacherUuid));
    }

    public void updateSubject(UUID subjectUuid, UUID currentTeacherUuid, CreateSubjectRequestDto createSubjectRequestDto) {
        Subject subject = subjectRepository.findById(subjectUuid).orElseThrow(NoSuchElementException::new);

        log.info("Got subject with UUID {}", subject.getId());

        if (!subjectDeputyRepository.existsByTeacherUuidAndSubjectId(currentTeacherUuid, subjectUuid)) {
            throw new IllegalCallerException();
        }

        subjectMapper.toEntity(createSubjectRequestDto, subject);
        subjectRepository.save(subject);
    }

    public List<SubjectOfStudentDto> getSubjectsOfCurrentSemester() {
        SemesterDto currentSemester = semesterService.getCurrentSemester();
        Map<SubjectOfStudentDto, List<UUID>> collected = subjectRepository.findSubjectsByRegisterablePeriodOfYear(PeriodOfYear.valueOf(currentSemester.getPeriodOfYear().toString())).stream()
                .collect(Collectors.toMap(
                        subjectMapper::toStudentDto,
                        subjectDeputyRepository::findTeacherUuidsBySubject)
                );

        collected.forEach((dto, subjectDeputyUuids) -> {
            List<String> teacherNameList = userApi.getTeachers(subjectDeputyUuids).stream()
                    .map(teacher ->
                            String.format("%s %s", teacher.getFirstName(), teacher.getLastName())
                    )
                    .toList();
            dto.setSubjectDeputies(teacherNameList);
        });

        return collected.keySet().stream().toList();
    }

    public List<SubjectRegistrationDto> getSubjectRegistrationsOfCurrentSemester(UUID currentStudentUuid) {
        SemesterRegistration currentSemesterRegistration = semesterService.getCurrentSemesterRegistrationForStudent(currentStudentUuid);

        return subjectMapper.toDtos(subjectRegistrationRepository.findAllBySemesterRegistration(currentSemesterRegistration));
    }

    public void registerForCurrentSemester(UUID subjectUuid, UUID studentUuid) {
        SemesterRegistration registration = semesterService.getCurrentSemesterRegistrationForStudent(studentUuid);
        Subject subject = getSubject(subjectUuid);

        SubjectRegistration subjectRegistration = new SubjectRegistration();
        subjectRegistration.setSubject(subject);
        subjectRegistration.setStudentUuid(studentUuid);
        subjectRegistration.setSemesterRegistration(registration);

        registration.addSubjectRegistration(subjectRegistrationRepository.save(subjectRegistration));
        subject.addSubjectRegistration(subjectRegistration);
    }

    public List<UUID> getRegisteredStudents(UUID subjectUuid) {
        return subjectRegistrationRepository.findAllBySubjectId(subjectUuid).stream()
                .map(SubjectRegistration::getStudentUuid)
                .toList();
    }

    public void removeStudentRegistration(UUID subjectUuid, UUID studentUuid) {
        SemesterRegistration semesterRegistration = semesterService.getCurrentSemesterRegistrationForStudent(studentUuid);
        Subject subject = getSubject(subjectUuid);
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.findBySubjectIdAndSemesterRegistrationAndStudentUuid(subjectUuid, semesterRegistration, studentUuid);


        if (subjectRegistration != null) {
            log.info("Removing subject registration for subject {} and student {}", subjectRegistration.getSubject().getId(), subjectRegistration.getStudentUuid());
            semesterRegistration.removeSubjectRegistration(subjectRegistration);
            subject.removeSubjectRegistration(subjectRegistration);

            subjectRegistrationRepository.delete(subjectRegistration);
            courseService.removeStudentRegistrationForSubject(subjectUuid, studentUuid);
        } else {
            log.info("No subject registration found for student {} for subject {}", studentUuid, subjectUuid);
        }
    }

    public List<SubjectRegistrationOfStudentDto> getSubjectsOfSemester(UUID semesterUuid, UUID currentStudentUuid) {
        Semester semester = semesterService.getSemester(semesterUuid);

        return subjectMapper.toStudentDtos(subjectRegistrationRepository.findAllBySemesterRegistrationSemesterAndStudentUuid(semester, currentStudentUuid));
    }
}
