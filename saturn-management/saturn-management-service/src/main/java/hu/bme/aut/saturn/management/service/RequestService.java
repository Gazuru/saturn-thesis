package hu.bme.aut.saturn.management.service;

import hu.bme.aut.saturn.management.mapper.RequestMapper;
import hu.bme.aut.saturn.management.persistence.entity.Request;
import hu.bme.aut.saturn.management.persistence.repository.ManagerRepository;
import hu.bme.aut.saturn.management.persistence.repository.RequestRepository;
import hu.bme.aut.saturn.management.service.v1.CreateRequestRequestDto;
import hu.bme.aut.saturn.management.service.v1.RequestOfManagerDto;
import hu.bme.aut.saturn.management.service.v1.RequestOfStudentDto;
import hu.bme.aut.saturn.management.service.v1.UpdateRequestRequestDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestService {

    private final RequestRepository requestRepository;

    private final ManagerRepository managerRepository;

    private final UserService userService;

    private final RequestMapper requestMapper;

    public void createRequest(CreateRequestRequestDto createRequestRequestDto, UUID currentStudentUuid) {
        Request request = requestMapper.toEntity(createRequestRequestDto, currentStudentUuid);
        request.setAssignee(managerRepository.findById(UUID.fromString("61A2AEE1-1B0E-4220-9B35-59C62791CA1A")).orElseThrow(NoSuchElementException::new));
        request.setStatus(Request.Status.NEW);
        requestRepository.save(request);
    }

    public List<RequestOfStudentDto> getRequests(UUID currentStudentUuid) {
        return requestRepository.findAllByRequesterUuid(currentStudentUuid).stream()
                .map(request ->
                        {
                            String nameOfManager = userService.getNameOfManager(request.getAssignee().getId());
                            return requestMapper.toDto(request, nameOfManager);
                        }
                )
                .toList();
    }

    public List<RequestOfManagerDto> getRequestsOfManager(UUID currentManagerUuid) {
        return requestRepository.findAllByAssigneeIdAndNotCompleted(currentManagerUuid).stream()
                .map(request ->
                {
                    String nameOfStudent = userService.getNameOfStudent(request.getRequesterUuid());
                    return requestMapper.toManagerDto(request, nameOfStudent);
                })
                .toList();
    }

    public void updateRequest(UUID requestUuid, UpdateRequestRequestDto updateRequestRequestDto) {
        Request request = requestRepository.findById(requestUuid).orElseThrow(NoSuchElementException::new);
        requestMapper.toEntity(updateRequestRequestDto, request);

        requestRepository.save(request);
    }
}
