package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.entities.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(User user, Long userId);

    void deleteUser(Long id);

}
