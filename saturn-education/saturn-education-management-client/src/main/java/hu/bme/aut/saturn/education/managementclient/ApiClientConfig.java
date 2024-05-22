package hu.bme.aut.saturn.education.managementclient;

import hu.bme.aut.saturn.education.managementclient.v1.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApiClientConfig {

    private final RestTemplate restTemplate;

    @Bean
    UserApi userApi() {
        return new UserApi(new ApiClient(restTemplate));
    }
}
