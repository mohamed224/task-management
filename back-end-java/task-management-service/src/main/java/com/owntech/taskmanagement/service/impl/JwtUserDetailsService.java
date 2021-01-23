package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.exceptions.ApiErrors;
import com.owntech.taskmanagement.exceptions.HttpCustomException;
import com.owntech.taskmanagement.util.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public JwtUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return JwtUserFactory.create(user);
        }
        throw new HttpCustomException(ApiErrors.OBJECT_NOT_FOUND_STATUS_CODE, String.format(ApiErrors.OBJECT_NOT_FOUND_MESSAGE, username));
    }
}
