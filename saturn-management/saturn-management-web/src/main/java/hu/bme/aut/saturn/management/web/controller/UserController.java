package hu.bme.aut.saturn.management.web.controller;

import hu.bme.aut.saturn.management.service.UserService;
import hu.bme.aut.saturn.management.service.v1.UserDto;
import hu.bme.aut.saturn.management.web.v1.UserApi;
import java.util.List;
import java.util.UUID;
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
    public ResponseEntity<List<UserDto>> getStudents(List<UUID> studentUuids) {
        return ResponseEntity.ok(userService.getStudents(studentUuids));
    }

    @Override
    public ResponseEntity<List<UserDto>> getTeachers(List<UUID> teacherUuids) {
        return ResponseEntity.ok(userService.getTeachers(teacherUuids));
    }

    @Override
    public ResponseEntity<UserDto> getUser(String saturnCode) {
        log.info("Getting user");
        return ResponseEntity.ofNullable(userService.getUserBySaturnCode(saturnCode));
    }
}
