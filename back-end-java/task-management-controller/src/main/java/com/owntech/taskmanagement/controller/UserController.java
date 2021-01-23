package com.owntech.taskmanagement.controller;

import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.service.IUserService;
import com.owntech.taskmanagement.service.impl.JwtTokenUtil;
import com.owntech.taskmanagement.service.impl.JwtUserDetailsService;
import com.owntech.taskmanagement.util.AuthenticationRequest;
import com.owntech.taskmanagement.util.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception(e.getMessage());
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
