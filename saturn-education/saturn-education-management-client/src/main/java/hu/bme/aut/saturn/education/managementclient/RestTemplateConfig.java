package hu.bme.aut.saturn.education.managementclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(((request, body, execution) -> {
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    if (securityContext != null) {
                        Authentication authentication = securityContext.getAuthentication();
                        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                            String tokenValue = jwtAuthenticationToken.getToken().getTokenValue();
                            request.getHeaders().add("Authorization", "Bearer " + tokenValue);
                        }
                    }
                    return execution.execute(request, body);
                }))
                .build();
    }

}
