package hu.bme.aut.saturn.shared.auth;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class SaturnUserAuthenticationToken extends JwtAuthenticationToken {

    public final transient SaturnProfile profile;

    public SaturnUserAuthenticationToken(Jwt source, Collection<? extends GrantedAuthority> grantedAuthorities, SaturnProfile profile) {
        super(source, grantedAuthorities);
        this.profile = profile;
    }
}
