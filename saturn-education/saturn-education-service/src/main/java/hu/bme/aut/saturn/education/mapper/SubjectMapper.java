package hu.bme.aut.saturn.education.mapper;

import hu.bme.aut.saturn.education.persistence.entity.Subject;
import hu.bme.aut.saturn.education.persistence.relation.SubjectRegistration;
import hu.bme.aut.saturn.education.service.v1.*;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "name", source = "subjectName")
    @Mapping(target = "creditIndex", source = "creditAmount")
    @Mapping(target = "registerablePeriodOfYear", source = "period")
    @Mapping(target = "subjectDeputies", ignore = true)
    Subject toEntity(CreateSubjectRequestDto requestDto);

    @Mapping(target = "name", source = "subjectName")
    @Mapping(target = "creditIndex", source = "creditAmount")
    @Mapping(target = "registerablePeriodOfYear", source = "period")
    @Mapping(target = "subjectDeputies", ignore = true)
    Subject toEntity(CreateSubjectRequestDto requestDto, @MappingTarget Subject subject);

    SubjectOfTeacherDto toTeacherDto(Subject subject);

    List<SubjectOfTeacherDto> toTeacherDtos(Collection<Subject> subjects);

    @Mapping(target = "subjectDeputies", ignore = true)
    SubjectOfStudentDto toStudentDto(Subject subject);

    List<SubjectOfStudentDto> toStudentDtos(Collection<Subject> subjects);

    @Mapping(target = "subjectUuid", source = "subject.id")
    SubjectRegistrationDto toDto(SubjectRegistration subjectRegistration);

    List<SubjectRegistrationDto> toDtos(Collection<SubjectRegistration> subjectRegistrations);

    @Mapping(target = "name", source = "subject.name")
    @Mapping(target = "creditIndex", source = "subject.creditIndex")
    SubjectRegistrationOfStudentDto toStudentDto(SubjectRegistration subjectRegistration);

    List<SubjectRegistrationOfStudentDto> toStudentDtos(List<SubjectRegistration> subjectRegistrations);
}
