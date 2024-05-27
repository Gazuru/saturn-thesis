package hu.bme.aut.saturn.education.service;

import hu.bme.aut.saturn.education.mapper.ExamMapper;
import hu.bme.aut.saturn.education.persistence.entity.Exam;
import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.relation.ExamRegistration;
import hu.bme.aut.saturn.education.persistence.repository.ExamRegistrationRepository;
import hu.bme.aut.saturn.education.persistence.repository.ExamRepository;
import hu.bme.aut.saturn.education.service.v1.CreateExamForSubjectRequestDto;
import hu.bme.aut.saturn.education.service.v1.ExamDto;
import hu.bme.aut.saturn.education.service.v1.ExamRegistrationDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {

    private final ExamRepository examRepository;

    private final ExamRegistrationRepository examRegistrationRepository;

    private final SubjectService subjectService;

    private final ExamMapper examMapper;

    public void createExamForSubject(UUID subjectUuid, CreateExamForSubjectRequestDto createExamForSubjectRequestDto) {
        Subject subject = subjectService.getSubject(subjectUuid);

        Exam exam = examMapper.toEntity(createExamForSubjectRequestDto);
        exam.setSubject(subject);

        subject.addExam(examRepository.save(exam));
    }

    public List<ExamDto> getExamsOfSubject(UUID subjectUuid) {
        return examMapper.toDtos(examRepository.findAllBySubjectId(subjectUuid));
    }

    public List<ExamDto> getExams() {
        return examMapper.toDtos(examRepository.findAll());
    }

    public List<ExamRegistrationDto> getExamRegistrations(UUID currentStudentUuid) {
        return examMapper.toDtos(examRegistrationRepository.findAllByStudentUuid(currentStudentUuid));
    }

    public void registerForExam(UUID examUuid, UUID currentStudentUuid) {
        Exam exam = examRepository.findById(examUuid).orElseThrow(NoSuchElementException::new);

        ExamRegistration examRegistration = new ExamRegistration();
        examRegistration.setStudentUuid(currentStudentUuid);
        examRegistration.setExam(exam);
        
        exam.addExamRegistration(examRegistrationRepository.save(examRegistration));
    }
}
