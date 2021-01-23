package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.dto.UserDto;
import com.owntech.taskmanagement.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UserConverter {

    private UserConverter() {

    }

    public static UserDto modelToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    public static User dtoToModel(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), false);
        user.setUsername(userDto.getEmail());
        return user;
    }

    public static List<UserDto> modelsToDtos(Collection<User> users) {
        return users.stream().filter(Objects::nonNull).map(UserConverter::modelToDto).collect(Collectors.toList());
    }
}
