package hu.bme.aut.saturn.management.web.controller;

import hu.bme.aut.saturn.management.service.RequestService;
import hu.bme.aut.saturn.management.service.v1.CreateRequestRequestDto;
import hu.bme.aut.saturn.management.service.v1.RequestOfManagerDto;
import hu.bme.aut.saturn.management.service.v1.RequestOfStudentDto;
import hu.bme.aut.saturn.management.service.v1.UpdateRequestRequestDto;
import hu.bme.aut.saturn.management.web.v1.RequestApi;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RequestController implements RequestApi {

    private final UserApiHelper userApiHelper;

    private final RequestService requestService;

    @Override
    public ResponseEntity<Void> createRequest(CreateRequestRequestDto createRequestRequestDto) throws Exception {
        requestService.createRequest(createRequestRequestDto, userApiHelper.getCurrentStudentUuid());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<RequestOfManagerDto>> getMyRequests() {
        return ResponseEntity.ok(requestService.getRequestsOfManager(userApiHelper.getCurrentManagerUuid()));
    }

    @Override
    public ResponseEntity<List<RequestOfStudentDto>> getRequests() {
        return ResponseEntity.ok(requestService.getRequests(userApiHelper.getCurrentStudentUuid()));
    }

    @Override
    public ResponseEntity<Void> updateRequest(UUID requestUuid, UpdateRequestRequestDto updateRequestRequestDto) throws Exception {
        requestService.updateRequest(requestUuid, updateRequestRequestDto);
        return ResponseEntity.ok().build();
    }
}
