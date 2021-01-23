package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    UserDto getUserById(Long id);

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void deleteUser(Long id);

}
