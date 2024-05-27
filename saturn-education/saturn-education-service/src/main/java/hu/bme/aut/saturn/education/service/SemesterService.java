package hu.bme.aut.saturn.education.service;

import hu.bme.aut.saturn.education.mapper.SemesterMapper;
import hu.bme.aut.saturn.education.persistence.entity.Semester;
import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import hu.bme.aut.saturn.education.persistence.repository.SemesterRegistrationRepository;
import hu.bme.aut.saturn.education.persistence.repository.SemesterRepository;
import hu.bme.aut.saturn.education.service.v1.RegisterForCurrentSemesterRequestDto;
import hu.bme.aut.saturn.education.service.v1.SemesterDto;
import hu.bme.aut.saturn.education.service.v1.SemesterRegistrationDto;
import hu.bme.aut.saturn.education.service.v1.SemesterRegistrationOfStudentDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SemesterService {

    private final SemesterRepository semesterRepository;

    private final SemesterRegistrationRepository semesterRegistrationRepository;

    private final SemesterMapper semesterMapper;

    public SemesterDto getCurrentSemester() {
        return semesterMapper.toDto(semesterRepository.findCurrentSemester());
    }

    public void registerForCurrentSemester(UUID studentUuid, RegisterForCurrentSemesterRequestDto registerForCurrentSemesterRequestDto) {
        RegisterForCurrentSemesterRequestDto.StatusEnum status = registerForCurrentSemesterRequestDto.getStatus();
        Semester currentSemester = semesterRepository.findCurrentSemester();

        System.out.println("Getting current registration");

        SemesterRegistration registration = getCurrentSemesterRegistrationForStudent(studentUuid);

        if (registration == null) {
            registration = new SemesterRegistration();
            registration.setSemester(currentSemester);
            registration.setStudentUuid(studentUuid);
        } else {
            log.info("Current semester registration for student {} already exists, updating instead.", studentUuid);
        }

        registration.setStatus(SemesterRegistration.Status.valueOf(status.getValue()));

        semesterRegistrationRepository.save(registration);
    }

    public SemesterRegistrationDto getCurrentSemesterRegistrationDto(UUID studentUuid) {
        return semesterMapper.toDto(getCurrentSemesterRegistrationForStudent(studentUuid));
    }

    public SemesterRegistration getCurrentSemesterRegistrationForStudent(UUID studentUuid) {
        return semesterRegistrationRepository.findBySemesterIdAndStudentUuid(semesterRepository.findCurrentSemester().getId(), studentUuid);
    }

    public List<SemesterRegistrationOfStudentDto> getSemesterRegistrations(UUID currentStudentUuid) {
        return semesterMapper.toStudentDtos(semesterRegistrationRepository.findAllByStudentUuid(currentStudentUuid));
    }

    public Semester getSemester(UUID semesterUuid) {
        return semesterRepository.findById(semesterUuid).orElseThrow(NoSuchElementException::new);
    }
}
