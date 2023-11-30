package hu.bme.aut.saturn.education.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DummyService {

    public String test() {
        log.info("Test message for DummyService.");
        return "Test return value for DummyService.";
    }
}
