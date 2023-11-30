package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.DummyService;
import hu.bme.aut.saturn.education.web.v1.DummyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DummyController implements DummyApi {

    private final DummyService dummyService;

    @Override
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok(dummyService.test());
    }
}
