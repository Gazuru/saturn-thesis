package hu.bme.aut.saturn.shared.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;

@RequiredArgsConstructor
class SaturnProfileExtractor {

    SaturnProfile getCurrentProfile(Jwt jwt) throws InvalidBearerTokenException {
        Object resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null) {
            throw new InvalidBearerTokenException("Resource access required");
        }

        HashMap<String, Map<String, List<String>>> resourceAccessMap = new HashMap<>(jwt.getClaim("resource_access"));

        Map<String, List<String>> saturn = resourceAccessMap.get("saturn");

        if (saturn == null || saturn.isEmpty()) {
            throw new InvalidBearerTokenException("Saturn required");
        }

        List<String> roles = saturn.get("roles");

        if (roles == null || roles.isEmpty()) {
            throw new InvalidBearerTokenException("Roles required");
        }


        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        String preferredUsername = jwt.getClaim("preferred_username");

        String givenName = jwt.getClaim("given_name");

        String familyName = jwt.getClaim("family_name");

        String email = jwt.getClaim("email");

        return new SaturnProfile(preferredUsername,
                grantedAuthorities,
                null);
    }
}
