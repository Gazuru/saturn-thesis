package hu.bme.aut.saturn.management.web.controller;

import hu.bme.aut.saturn.management.service.UserService;
import hu.bme.aut.saturn.management.service.v1.UserDto;
import hu.bme.aut.saturn.management.web.v1.UserApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUser(String saturnCode) {
        log.info("Getting current user with saturnCode {}", saturnCode);
        return ResponseEntity.ofNullable(userService.getUserBySaturnCode(saturnCode));
    }
}
