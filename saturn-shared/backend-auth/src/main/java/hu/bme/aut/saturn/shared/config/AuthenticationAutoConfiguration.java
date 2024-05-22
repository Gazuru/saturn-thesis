package hu.bme.aut.saturn.shared.config;

import hu.bme.aut.saturn.shared.auth.KeycloakJwtAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@Slf4j
public class AuthenticationAutoConfiguration {

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity(securedEnabled = true)
    static class SecurityConfig {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
            tokenResolver.setAllowUriQueryParameter(true);
            http
                    .cors(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().authenticated()
                    )
                    .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .bearerTokenResolver(tokenResolver)
                            .jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())));
            return http.build();
        }
    }

    @Slf4j
    @Configuration
    @Profile("local")
    static class CorsConfiguration {
        private static final String FE_LOCAL = "http://localhost:4200";

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            log.warn("Configuring CORS for " + FE_LOCAL);
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins(FE_LOCAL)
                            .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), HttpMethod.PATCH.name());
                }
            };
        }
    }
}