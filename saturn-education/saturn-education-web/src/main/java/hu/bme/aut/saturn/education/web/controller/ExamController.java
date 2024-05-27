package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.ExamService;
import hu.bme.aut.saturn.education.service.v1.CreateExamForSubjectRequestDto;
import hu.bme.aut.saturn.education.service.v1.ExamDto;
import hu.bme.aut.saturn.education.service.v1.ExamRegistrationDto;
import hu.bme.aut.saturn.education.web.v1.ExamApi;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExamController implements ExamApi {

    private final ExamService examService;
    private final UserApiHelper userApiHelper;

    @Override
    public ResponseEntity<Void> createExamForSubject(UUID subjectUuid, CreateExamForSubjectRequestDto createExamForSubjectRequestDto) throws Exception {
        examService.createExamForSubject(subjectUuid, createExamForSubjectRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ExamRegistrationDto>> getAllExamRegistrations() {
        return ResponseEntity.ok(examService.getExamRegistrations(userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(examService.getExams());
    }

    @Override
    public ResponseEntity<List<ExamDto>> getExamsOfSubject(UUID subjectUuid) {
        return ResponseEntity.ok(examService.getExamsOfSubject(subjectUuid));
    }

    @Override
    public ResponseEntity<Void> registerForExam(UUID examUuid) throws Exception {
        examService.registerForExam(examUuid, userApiHelper.getCurrentStudentUuid());
        return ResponseEntity.ok().build();
    }
}
