package com.owntech.taskmanagement.dao;

import com.owntech.taskmanagement.entities.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends BaseDao<Permission, Long> {
}
