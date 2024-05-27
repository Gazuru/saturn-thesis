package hu.bme.aut.saturn.management.service;

import hu.bme.aut.saturn.management.mapper.UserMapper;
import hu.bme.aut.saturn.management.persistence.repository.UserRepository;
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
}
