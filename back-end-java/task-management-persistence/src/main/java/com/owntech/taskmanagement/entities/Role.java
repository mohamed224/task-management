package com.owntech.taskmanagement.entities;

import com.owntech.taskmanagement.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "T_ROLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"isDeleted"})
@EntityListeners(AuditingEntityListener.class)
public class Role extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Long id;
    @Column(name = "ROL_NAME")
    private String name;
    @Column(name = "ROL_DESCRIPTION")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TJ_ROL_PERMISSION", joinColumns = {
            @JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
    }, inverseJoinColumns = {@JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")})
    private Set<Permission> permissions;

    @Column(name = "ROL_IS_DELETED", columnDefinition = "bit default 0")
    private boolean isDeleted;
}
