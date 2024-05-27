package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.CourseService;
import hu.bme.aut.saturn.education.service.v1.CourseDto;
import hu.bme.aut.saturn.education.service.v1.CourseRegistrationDto;
import hu.bme.aut.saturn.education.service.v1.CreateCourseForSubjectRequestDto;
import hu.bme.aut.saturn.education.web.v1.CourseApi;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController implements CourseApi {

    private final CourseService courseService;

    private final UserApiHelper userApiHelper;

    @Override
    public ResponseEntity<Void> createCourseForSubject(UUID subjectUuid, CreateCourseForSubjectRequestDto createCourseForSubjectRequestDto) {
        courseService.createCourseForSubject(subjectUuid, createCourseForSubjectRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CourseRegistrationDto>> getCourseRegistrationsOfSubject(UUID subjectUuid) {
        return ResponseEntity.ok(courseService.getCourseRegistrationsOfSubject(subjectUuid, userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<List<CourseDto>> getCoursesOfCurrentSemester() {
        return ResponseEntity.ok(courseService.getCoursesOfCurrentSemester(userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<List<CourseDto>> getCoursesOfSubject(UUID subjectUuid) {
        return ResponseEntity.ok(courseService.getCoursesOfSubject(subjectUuid));
    }

    @Override
    public ResponseEntity<Void> registerForCourse(UUID courseUuid) {
        courseService.registerForCourse(courseUuid, userApiHelper.getCurrentStudentUuid());
        return ResponseEntity.ok().build();
    }
}
