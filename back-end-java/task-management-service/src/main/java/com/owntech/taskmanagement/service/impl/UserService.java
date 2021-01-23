package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.converter.UserConverter;
import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.dto.UserDto;
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
    public List<UserDto> getUsers() {
        return UserConverter.modelsToDtos(userDao.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            return UserConverter.modelToDto(optionalUser.get());
        }
        throw new RuntimeException("User doesn't exist");
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        return UserConverter.modelToDto(userDao.saveAndFlush(UserConverter.dtoToModel(userDto)));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        userDto.setId(userId);
        return UserConverter.modelToDto(userDao.saveAndFlush(UserConverter.dtoToModel(userDto)));
    }

    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
