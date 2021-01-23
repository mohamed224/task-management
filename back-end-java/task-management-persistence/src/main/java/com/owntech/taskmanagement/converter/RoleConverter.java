package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.dto.RoleDto;
import com.owntech.taskmanagement.entities.Role;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RoleConverter {
    private RoleConverter() {
    }

    public static RoleDto modelToDto(Role role) {
        return new RoleDto(role.getId(), role.getName(), role.getDescription(), role.getPermissions());
    }

    public static Role dtoToModel(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getName(), roleDto.getDescription(), roleDto.getPermissions(), false);
    }

    public static List<RoleDto> modelsToDtos(Collection<Role> roles) {
        return roles.stream().filter(Objects::nonNull).map(RoleConverter::modelToDto).collect(Collectors.toList());
    }
}
