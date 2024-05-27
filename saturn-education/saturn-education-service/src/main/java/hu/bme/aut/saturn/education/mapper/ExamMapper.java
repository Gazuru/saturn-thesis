package hu.bme.aut.saturn.education.mapper;

import hu.bme.aut.saturn.education.persistence.entity.Exam;
import hu.bme.aut.saturn.education.persistence.relation.ExamRegistration;
import hu.bme.aut.saturn.education.service.v1.CreateExamForSubjectRequestDto;
import hu.bme.aut.saturn.education.service.v1.ExamDto;
import hu.bme.aut.saturn.education.service.v1.ExamRegistrationDto;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mapping(target = "subjectUuid", source = "subject.id")
    ExamDto toDto(Exam exam);

    List<ExamDto> toDtos(List<Exam> subjects);

    Exam toEntity(CreateExamForSubjectRequestDto createExamForSubjectRequestDto);

    @Mapping(target = "examUuid", source = "exam.id")
    ExamRegistrationDto toDto(ExamRegistration examRegistration);

    List<ExamRegistrationDto> toDtos(Collection<ExamRegistration> examRegistrations);

}
