package com.owntech.taskmanagement.controller;

import com.owntech.taskmanagement.dto.RoleDto;
import com.owntech.taskmanagement.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleDto saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @GetMapping
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.updateRole(roleDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

}
