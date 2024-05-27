package hu.bme.aut.saturn.management.mapper;

import hu.bme.aut.saturn.management.persistence.entity.Request;
import hu.bme.aut.saturn.management.service.v1.*;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "assignee", source = "request.assignee.id")
    RequestDto toDto(Request request);

    @Mapping(target = "requesterUuid", source = "studentUuid")
    Request toEntity(CreateRequestRequestDto requestDto, UUID studentUuid);

    @Mapping(target = ".", source = "request")
    @Mapping(target = "assignee", source = "assigneeName")
    RequestOfStudentDto toDto(Request request, String assigneeName);

    @Mapping(target = ".", source = "request")
    @Mapping(target = "requesterName", source = "nameOfRequester")
    RequestOfManagerDto toManagerDto(Request request, String nameOfRequester);

    List<RequestOfManagerDto> toManagerDtos(List<Request> requests);

    Request toEntity(UpdateRequestRequestDto requestDto, @MappingTarget Request target);
}
