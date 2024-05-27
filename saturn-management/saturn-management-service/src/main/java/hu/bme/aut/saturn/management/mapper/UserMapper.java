package hu.bme.aut.saturn.management.mapper;

import hu.bme.aut.saturn.management.persistence.entity.Manager;
import hu.bme.aut.saturn.management.persistence.entity.Student;
import hu.bme.aut.saturn.management.persistence.entity.Teacher;
import hu.bme.aut.saturn.management.persistence.entity.User;
import hu.bme.aut.saturn.management.service.v1.ManagerDto;
import hu.bme.aut.saturn.management.service.v1.StudentDto;
import hu.bme.aut.saturn.management.service.v1.TeacherDto;
import hu.bme.aut.saturn.management.service.v1.UserDto;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(uses = {RequestMapper.class, DocumentMapper.class}, componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtos(Collection<User> users);

    StudentDto toDto(Student student);

    ManagerDto toDto(Manager manager);

    TeacherDto toDto(Teacher teacher);
}
