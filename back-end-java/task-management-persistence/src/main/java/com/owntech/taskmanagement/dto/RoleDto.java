package com.owntech.taskmanagement.dto;

import com.owntech.taskmanagement.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class RoleDto {
    private Long id;
    private String name;
    private String description;
    private Set<Permission> permissions;
}
