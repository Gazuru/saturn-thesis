package hu.bme.aut.saturn.management.web.controller;

import hu.bme.aut.saturn.management.service.v1.UserDto;
import hu.bme.aut.saturn.shared.auth.SaturnUserAuthenticationToken;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserApiHelper {

    private final UserController userController;

    private UserDto getUser() {
        SaturnUserAuthenticationToken authentication = (SaturnUserAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String saturnCode = authentication.profile.saturnCode();

        UserDto user = userController.getUser(saturnCode).getBody();

        if (user == null) {
            throw new IllegalArgumentException("Unable to find user with saturnCode: " + saturnCode);
        }

        return user;
    }

    public UUID getCurrentUserUuid() {
        return getUser().getId();
    }

    public UUID getCurrentStudentUuid() {
        return getUser().getStudentInformation().getId();
    }

    public UUID getCurrentTeacherUuid() {
        return getUser().getTeacherInformation().getId();
    }

    public UUID getCurrentManagerUuid() {
        return getUser().getManagerInformation().getId();
    }

}