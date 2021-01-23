package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.util.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return JwtUserFactory.create(user);
        }
        throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    }
}
