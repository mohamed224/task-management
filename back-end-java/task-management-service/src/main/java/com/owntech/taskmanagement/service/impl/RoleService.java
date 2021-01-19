package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.RoleDao;
import com.owntech.taskmanagement.entities.Role;
import com.owntech.taskmanagement.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends GenericService<Role, Long> implements IRoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> optionalRole = roleDao.findById(id);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        throw new RuntimeException("Role doesn't exist");
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.saveAndFlush(role);
    }

    @Override
    public Role updateRole(Role role, Long roleId) {
        role.setId(roleId);
        return roleDao.saveAndFlush(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.delete(id);
    }
}
