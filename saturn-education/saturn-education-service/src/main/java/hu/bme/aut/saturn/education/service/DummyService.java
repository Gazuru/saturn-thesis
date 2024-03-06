package hu.bme.aut.saturn.education.service;

import hu.bme.aut.saturn.education.persistence.entity.User;
import hu.bme.aut.saturn.education.service.v1.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DummyService {

    public UserDto test() {
        log.info("Test message for DummyService.");
        return new UserDto();
    }
}
