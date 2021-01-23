package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.dto.RoleDto;

import java.util.List;

public interface IRoleService {

    List<RoleDto> getRoles();

    RoleDto getRoleById(Long id);

    RoleDto saveRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Long roleId);

    void deleteRole(Long id);
}
