package hu.bme.aut.saturn.education.web.controller;

import hu.bme.aut.saturn.education.service.SemesterService;
import hu.bme.aut.saturn.education.service.v1.RegisterForCurrentSemesterRequestDto;
import hu.bme.aut.saturn.education.service.v1.SemesterDto;
import hu.bme.aut.saturn.education.service.v1.SemesterRegistrationDto;
import hu.bme.aut.saturn.education.web.v1.SemesterApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SemesterController implements SemesterApi {

    private final SemesterService semesterService;

    private final UserApiHelper userApiHelper;

    @Override
    public ResponseEntity<SemesterDto> getCurrentSemester() {
        return ResponseEntity.ok(semesterService.getCurrentSemester());
    }

    @Override
    public ResponseEntity<SemesterRegistrationDto> getCurrentSemesterRegistration() {
        return ResponseEntity.ofNullable(semesterService.getCurrentSemesterRegistration(userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<Void> registerForCurrentSemester(RegisterForCurrentSemesterRequestDto registerForCurrentSemesterRequestDto) {
        semesterService.registerForCurrentSemester(
                userApiHelper.getCurrentStudentUuid(),
                registerForCurrentSemesterRequestDto
        );
        return ResponseEntity.ok().build();
    }
}
