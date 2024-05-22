package hu.bme.aut.saturn.shared.auth;

import jakarta.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;

public record SaturnProfile(String saturnCode,
                            List<? extends GrantedAuthority> roles,
                            @Nullable UUID userUuid) {
}
