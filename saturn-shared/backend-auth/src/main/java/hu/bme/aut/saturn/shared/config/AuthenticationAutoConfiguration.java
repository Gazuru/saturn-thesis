package hu.bme.aut.saturn.shared.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@AutoConfiguration
public class AuthenticationAutoConfiguration {

    @Configuration
    @EnableWebSecurity
    static class SecurityConfig {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(authorizeRequests ->
                                    authorizeRequests
                                            .anyRequest().permitAll()
//                                    .anyRequest().hasAuthority("admin")
//                                    .requestMatchers("/api/v1/education/**").hasAnyAuthority("student", "teacher")
//                                    .requestMatchers("/api/v1/management/**").hasAuthority("manager")
//                                    .requestMatchers("/api/v1/portal/**").authenticated()
//                                    .anyRequest().denyAll()
                    )
                    .cors(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                    .formLogin(Customizer.withDefaults())
//                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            ;
            return http.build();
        }

        @Bean
        @Profile("local")
        UserDetailsService userDetailsService() {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            UserDetails userDetails = User.builder()
                    .username("admin")
                    .password("password")
                    .passwordEncoder(encoder::encode)
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(userDetails);
        }
    }
}