package com.owntech.taskmanagement.dao;

import com.owntech.taskmanagement.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Long> {
}
