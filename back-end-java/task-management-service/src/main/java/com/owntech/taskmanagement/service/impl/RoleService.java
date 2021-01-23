package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.converter.RoleConverter;
import com.owntech.taskmanagement.dao.RoleDao;
import com.owntech.taskmanagement.dto.RoleDto;
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
    public List<RoleDto> getRoles() {
        return RoleConverter.modelsToDtos(roleDao.findAll());
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Optional<Role> optionalRole = roleDao.findById(id);
        if (optionalRole.isPresent()) {
            return RoleConverter.modelToDto(optionalRole.get());
        }
        throw new RuntimeException("Role doesn't exist");
    }

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        return RoleConverter.modelToDto(roleDao.saveAndFlush(RoleConverter.dtoToModel(roleDto)));
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long roleId) {
        roleDto.setId(roleId);
        return RoleConverter.modelToDto(roleDao.saveAndFlush(RoleConverter.dtoToModel(roleDto)));
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.delete(id);
    }
}
