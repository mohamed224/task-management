package com.owntech.taskmanagement.dao;

import com.owntech.taskmanagement.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends BaseDao<User, Long> {

    Optional<User> findByUsername(String username);
}
