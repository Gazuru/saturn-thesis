package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.SubjectService;
import hu.bme.aut.saturn.education.service.v1.*;
import hu.bme.aut.saturn.education.web.v1.SubjectApi;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubjectController implements SubjectApi {

    private final SubjectService subjectService;

    private final UserApiHelper userApiHelper;

    @Override
    public ResponseEntity<Void> createSubject(CreateSubjectRequestDto createSubjectRequestDto) {
        subjectService.createSubject(createSubjectRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UUID>> getRegisteredStudents(UUID subjectUuid) {
        return ResponseEntity.ok(subjectService.getRegisteredStudents(subjectUuid));
    }

    @Override
    public ResponseEntity<List<SubjectRegistrationDto>> getSubjectRegistrationsOfCurrentSemester() {
        return ResponseEntity.ok(subjectService.getSubjectRegistrationsOfCurrentSemester(userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<List<SubjectOfStudentDto>> getSubjectsOfCurrentSemester() {
        return ResponseEntity.ok(subjectService.getSubjectsOfCurrentSemester());
    }

    @Override
    public ResponseEntity<List<SubjectRegistrationOfStudentDto>> getSubjectsOfSemester(UUID semesterUuid) {
        return ResponseEntity.ok(subjectService.getSubjectsOfSemester(semesterUuid, userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<List<SubjectOfTeacherDto>> getSubjectsOfTeacher() {
        return ResponseEntity.ok(subjectService.getSubjectsOfTeacher(userApiHelper.getCurrentTeacherUuid()));
    }

    @Override
    public ResponseEntity<Void> registerForSubjectOfCurrentSemester(UUID subjectUuid) {
        subjectService.registerForCurrentSemester(subjectUuid, userApiHelper.getCurrentStudentUuid());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeStudentRegistration(UUID subjectUuid, UUID studentUuid) {
        subjectService.removeStudentRegistration(subjectUuid, studentUuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateSubject(UUID subjectUuid, CreateSubjectRequestDto createSubjectRequestDto) {
        UUID currentTeacherUuid = userApiHelper.getCurrentTeacherUuid();
        try {
            log.info("Updating subject with uuid: {}", subjectUuid);
            subjectService.updateSubject(subjectUuid, currentTeacherUuid, createSubjectRequestDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
