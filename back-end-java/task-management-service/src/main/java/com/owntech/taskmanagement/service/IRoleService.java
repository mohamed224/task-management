package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.entities.Role;

import java.util.List;

public interface IRoleService {

    List<Role> getRoles();

    Role getRoleById(Long id);

    Role saveRole(Role role);

    Role updateRole(Role role, Long roleId);

    void deleteRole(Long id);
}
