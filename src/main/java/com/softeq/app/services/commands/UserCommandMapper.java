package com.softeq.app.services.commands;

import com.softeq.app.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserCommandMapper {

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget User user, UpdateUserCommand command);

}
