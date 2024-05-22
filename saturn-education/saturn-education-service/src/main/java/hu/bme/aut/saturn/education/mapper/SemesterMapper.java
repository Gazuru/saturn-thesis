package hu.bme.aut.saturn.education.mapper;

import hu.bme.aut.saturn.education.persistence.entity.Semester;
import hu.bme.aut.saturn.education.persistence.relation.SemesterRegistration;
import hu.bme.aut.saturn.education.service.v1.SemesterDto;
import hu.bme.aut.saturn.education.service.v1.SemesterRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

    SemesterDto toDto(Semester semester);

    @Mapping(target = "semesterUuid", source = "semester.id")
    SemesterRegistrationDto toDto(SemesterRegistration semesterRegistration);

}
