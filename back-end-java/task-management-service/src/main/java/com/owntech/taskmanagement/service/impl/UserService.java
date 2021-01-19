package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GenericService<User, Long> implements IUserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new RuntimeException("User doesn't exist");
    }

    @Override
    public User saveUser(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public User updateUser(User user, Long userId) {
        user.setId(userId);
        return userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
