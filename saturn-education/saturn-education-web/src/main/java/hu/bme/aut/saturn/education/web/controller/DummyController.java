package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.DummyService;
import hu.bme.aut.saturn.education.service.v1.UserDto;
import hu.bme.aut.saturn.education.web.v1.DummyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class DummyController implements DummyApi {

    private final DummyService dummyService;

    @Override
    public ResponseEntity<UserDto> getTest() {
        return ResponseEntity.ok(dummyService.test());
    }
}
