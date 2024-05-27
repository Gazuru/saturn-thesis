package hu.bme.aut.saturn.education.service;

import hu.bme.aut.saturn.education.managementclient.v1.UserApi;
import hu.bme.aut.saturn.education.mapper.CourseMapper;
import hu.bme.aut.saturn.education.persistence.entity.Course;
import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.relation.CourseRegistration;
import hu.bme.aut.saturn.education.persistence.relation.CourseTeacher;
import hu.bme.aut.saturn.education.persistence.repository.CourseRegistrationRepository;
import hu.bme.aut.saturn.education.persistence.repository.CourseRepository;
import hu.bme.aut.saturn.education.persistence.repository.CourseTeacherRepository;
import hu.bme.aut.saturn.education.service.v1.CourseDto;
import hu.bme.aut.saturn.education.service.v1.CourseRegistrationDto;
import hu.bme.aut.saturn.education.service.v1.CreateCourseForSubjectRequestDto;
import hu.bme.aut.saturn.education.service.v1.SubjectRegistrationDto;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseTeacherRepository courseTeacherRepository;

    private final CourseRegistrationRepository courseRegistrationRepository;

    private final CourseMapper courseMapper;

    private final SubjectService subjectService;

    private final UserApi userApi;

    public List<CourseDto> getCoursesOfSubject(UUID subjectUuid) {
        Map<CourseDto, List<UUID>> collected = courseRepository.findAllBySubjectId(subjectUuid).stream()
                .collect(Collectors.toMap(
                        courseMapper::toDto,
                        courseTeacherRepository::findTeacherUuidsByCourse)
                );

        collected.forEach((dto, courseTeacherUuids) -> {
            List<String> teacherNameList = userApi.getTeachers(courseTeacherUuids).stream()
                    .map(teacher ->
                            String.format("%s %s", teacher.getFirstName(), teacher.getLastName())
                    )
                    .toList();
            dto.setCourseTeachers(teacherNameList);
        });

        return collected.keySet().stream().toList();
    }

    public void createCourseForSubject(UUID subjectUuid, CreateCourseForSubjectRequestDto createCourseForSubjectRequestDto) {
        Subject subject = subjectService.getSubject(subjectUuid);

        Course course = courseMapper.toEntity(createCourseForSubjectRequestDto);
        course.setSubject(subject);
        createCourseDeputies(createCourseForSubjectRequestDto.getCourseDeputies(), courseRepository.save(course));

        subject.addCourse(course);
    }

    private void createCourseDeputies(List<UUID> courseDeputies, Course course) {
        courseDeputies.forEach(uuid -> {
            CourseTeacher courseTeacher = new CourseTeacher();
            courseTeacher.setCourse(course);
            courseTeacher.setTeacherUuid(uuid);

            course.addCourseTeacher(courseTeacherRepository.save(courseTeacher));
        });
    }

    public void registerForCourse(UUID courseUuid, UUID studentUuid) {
        Course course = courseRepository.findById(courseUuid).orElseThrow(NoSuchElementException::new);

        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setStudentUuid(studentUuid);

        course.addCourseRegistration(courseRegistrationRepository.save(courseRegistration));
    }

    public List<CourseRegistrationDto> getCourseRegistrationsOfSubject(UUID subjectUuid, UUID currentStudentUuid) {
        List<UUID> courseUuids = courseRepository.findAllBySubjectId(subjectUuid).stream()
                .map(Course::getId)
                .toList();

        return courseMapper.toDtos(courseRegistrationRepository.findAllByCourseIdInAndStudentUuid(courseUuids, currentStudentUuid));
    }

    public List<CourseDto> getCoursesOfCurrentSemester(UUID currentStudentUuid) {
        List<UUID> subjectUuids = subjectService.getSubjectRegistrationsOfCurrentSemester(currentStudentUuid).stream()
                .map(SubjectRegistrationDto::getSubjectUuid)
                .toList();

        List<UUID> courses = courseRepository.findAllBySubjectIdIn(subjectUuids).stream()
                .map(Course::getId)
                .toList();

        return courseRegistrationRepository.findAllByCourseIdInAndStudentUuid(courses, currentStudentUuid).stream()
                .map(CourseRegistration::getCourse)
                .map(courseMapper::toDto)
                .toList();
    }

    public void removeStudentRegistrationForSubject(UUID subjectUuid, UUID studentUuid) {
        List<Course> courses = courseRepository.findAllBySubjectId(subjectUuid);


        List<UUID> courseUuids = courses.stream()
                .map(Course::getId)
                .toList();

        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByCourseIdInAndStudentUuid(courseUuids, studentUuid);

        courseRegistrations.forEach(cr -> courses.forEach(course -> course.removeCourseRegistration(cr)));

        courseRegistrationRepository.deleteAll(courseRegistrations);
    }
}
