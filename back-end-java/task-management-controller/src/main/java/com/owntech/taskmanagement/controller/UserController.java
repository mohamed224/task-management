package com.owntech.taskmanagement.controller;

import com.owntech.taskmanagement.dto.UserDto;
import com.owntech.taskmanagement.exceptions.ApiErrors;
import com.owntech.taskmanagement.exceptions.HttpCustomException;
import com.owntech.taskmanagement.service.IUserService;
import com.owntech.taskmanagement.service.impl.JwtTokenUtil;
import com.owntech.taskmanagement.service.impl.JwtUserDetailsService;
import com.owntech.taskmanagement.util.AuthenticationRequest;
import com.owntech.taskmanagement.util.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(IUserService userService, AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new HttpCustomException(ApiErrors.BAD_CREDENTIALS_STATUS_CODE, ApiErrors.BAD_CREDENTIALS_MESSAGE);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('can_create_user')")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
