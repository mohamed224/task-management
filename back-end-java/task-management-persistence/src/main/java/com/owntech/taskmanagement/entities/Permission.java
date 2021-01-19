package com.owntech.taskmanagement.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PERMISSION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"isDeleted"})
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID")
    private Integer id;

    @Column(name = "PER_NAME")
    private String name;

    @Column(name = "PER_DESCRIPTION")
    private String description;

    @Column(name = "PER_IS_DELETED", columnDefinition = "bit default 0")
    private boolean isDeleted;
}
