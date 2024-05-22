package hu.bme.aut.saturn.shared.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final SaturnProfileExtractor profileExtractor;

    public KeycloakJwtAuthenticationConverter() {
        this.profileExtractor = new SaturnProfileExtractor();
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        SaturnProfile profile = profileExtractor.getCurrentProfile(source);
        
        return new SaturnUserAuthenticationToken(
                source,
                profile.roles(),
                profile
        );
    }
}
