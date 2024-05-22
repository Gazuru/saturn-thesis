package hu.bme.aut.saturn.management.service;

import hu.bme.aut.saturn.management.mapper.UserMapper;
import hu.bme.aut.saturn.management.persistence.repository.UserRepository;
import hu.bme.aut.saturn.management.service.v1.UserDto;
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
}
