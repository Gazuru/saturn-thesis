package hu.bme.aut.saturn.management.mapper;

import hu.bme.aut.saturn.management.persistence.entity.Request;
import hu.bme.aut.saturn.management.service.v1.RequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "assignee", source = "request.assignee.id")
    RequestDto toDto(Request request);
}
