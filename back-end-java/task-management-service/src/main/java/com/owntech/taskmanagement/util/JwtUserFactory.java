package com.owntech.taskmanagement.util;

import com.owntech.taskmanagement.entities.Permission;
import com.owntech.taskmanagement.entities.Role;
import com.owntech.taskmanagement.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static UserDetails create(User user) {
        return new JwtUser(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<Permission> permissions = new HashSet<>();
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getName());
            permissions.addAll(role.getPermissions());
        }
        for (Permission permission : permissions) {
            authorities.add(permission.getName());
        }
        return getGrantedAuthorities(authorities);
    }

    private static Collection<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
