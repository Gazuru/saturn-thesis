package hu.bme.aut.saturn.management.service;

import hu.bme.aut.saturn.management.mapper.UserMapper;
import hu.bme.aut.saturn.management.persistence.entity.Manager;
import hu.bme.aut.saturn.management.persistence.entity.Student;
import hu.bme.aut.saturn.management.persistence.entity.Teacher;
import hu.bme.aut.saturn.management.persistence.entity.User;
import hu.bme.aut.saturn.management.persistence.repository.ManagerRepository;
import hu.bme.aut.saturn.management.persistence.repository.StudentRepository;
import hu.bme.aut.saturn.management.persistence.repository.TeacherRepository;
import hu.bme.aut.saturn.management.persistence.repository.UserRepository;
import hu.bme.aut.saturn.management.service.v1.CreateUserRequestDto;
import hu.bme.aut.saturn.management.service.v1.UserDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final ManagerRepository managerRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public UserDto getUserBySaturnCode(String saturnCode) {
        return userMapper.toDto(userRepository.findUserBySaturnCode(saturnCode));
    }

    public List<UserDto> getTeachers(List<UUID> teacherUuids) {
        if (teacherUuids == null || teacherUuids.isEmpty()) {
            return userMapper.toDtos(userRepository.findUsersByTeacherInformationNotNull());
        }
        return userMapper.toDtos(userRepository.findUserByTeacherInformationIdIn(teacherUuids));
    }

    public List<UserDto> getStudents(List<UUID> studentUuids) {
        return userMapper.toDtos(userRepository.findUsersByStudentInformationIdIn(studentUuids));
    }

    public String getNameOfManager(UUID managerUuid) {
        return userRepository.getNameOfManager(managerUuid);
    }

    public String getNameOfStudent(UUID studentUuid) {
        return userRepository.getNameOfStudent(studentUuid);
    }

    public void createUser(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.toEntity(createUserRequestDto);
        List<CreateUserRequestDto.RolesEnum> roles = createUserRequestDto.getRoles();

        if (roles.contains(CreateUserRequestDto.RolesEnum.MANAGER)) {
            user.setManagerInformation(managerRepository.save(new Manager()));
        }
        if (roles.contains(CreateUserRequestDto.RolesEnum.TEACHER)) {
            user.setTeacherInformation(teacherRepository.save(new Teacher()));
        }
        if (roles.contains(CreateUserRequestDto.RolesEnum.STUDENT)) {
            user.setStudentInformation(studentRepository.save(new Student()));
        }

        userRepository.save(user);
    }
}
