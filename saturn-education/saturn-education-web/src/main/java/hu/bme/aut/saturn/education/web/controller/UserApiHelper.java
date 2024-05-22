package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.managementclient.v1.UserApi;
import hu.bme.aut.saturn.education.managementclient.v1.dto.UserDto;
import hu.bme.aut.saturn.shared.auth.SaturnUserAuthenticationToken;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserApiHelper {

    private final UserApi userApi;

    private UserDto getUser() {
        SaturnUserAuthenticationToken authentication = (SaturnUserAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String saturnCode = authentication.profile.saturnCode();

        UserDto user = userApi.getUser(saturnCode);

        if (user == null) {
            throw new IllegalArgumentException("Unable to find user with saturnCode: " + saturnCode);
        }

        return user;
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
