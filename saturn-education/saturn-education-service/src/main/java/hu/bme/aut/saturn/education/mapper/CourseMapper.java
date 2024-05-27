package hu.bme.aut.saturn.education.mapper;

import hu.bme.aut.saturn.education.persistence.entity.Course;
import hu.bme.aut.saturn.education.persistence.relation.CourseRegistration;
import hu.bme.aut.saturn.education.service.v1.CourseDto;
import hu.bme.aut.saturn.education.service.v1.CourseRegistrationDto;
import hu.bme.aut.saturn.education.service.v1.CreateCourseForSubjectRequestDto;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "courseTeachers", ignore = true)
    CourseDto toDto(Course course);

    List<CourseDto> toDtos(Collection<Course> courses);

    CourseDto.DayOfWeekEnum toDtoDayOfWeek(DayOfWeek dayOfWeek);

    DayOfWeek toDayOfWeek(CreateCourseForSubjectRequestDto.DayOfWeekEnum dayOfWeekEnum);

    @Mapping(target = "name", source = "courseName")
    Course toEntity(CreateCourseForSubjectRequestDto requestDto);

    @Mapping(target = "name", source = "courseName")
    Course toEntity(CreateCourseForSubjectRequestDto requestDto, @MappingTarget Course course);

    @Mapping(target = "courseUuid", source = "course.id")
    CourseRegistrationDto toDto(CourseRegistration courseRegistration);

    List<CourseRegistrationDto> toDtos(List<CourseRegistration> courseRegistrations);
}