package com.owntech.taskmanagement.entities;

import com.owntech.taskmanagement.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "T_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"isDeleted"})
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<String> implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_NAME", unique = true)
    private String username;

    @Column(name = "USR_FIRST_NAME")
    private String firstName;

    @Column(name = "USR_LAST_NAME")
    private String lastName;

    @Column(name = "USR_EMAIL", unique = true)
    private String email;

    @Column(name = "USR_PASSWORD")
    private String password;

    @Column(name = "USR_ENABLED")
    private boolean enabled;

    @Column(name = "USR_ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;

    @Column(name = "USR_ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;

    @Column(name = "USR_CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @ManyToMany
    @JoinTable(name = "TJ_USR_ROLE", joinColumns = {
            @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
            })
    private Set<Role> roles;

    @ManyToMany(mappedBy = "users")
    private Set<Task> tasks;

    @Column(name = "USR_IS_DELETED", columnDefinition = "bit default 0")
    private boolean isDeleted;


}
