package com.owntech.taskmanagement.entities;

import com.owntech.taskmanagement.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "T_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"isDeleted"})
@EntityListeners(AuditingEntityListener.class)
public class Category extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_ID")
    private Long id;
    @Column(name = "CAT_NAME")
    private String name;
    @Column(name = "CAT_DESCRIPTION")
    private String description;
    @Column(name = "CAT_TASK")
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Task> tasks;
    @Column(name = "CAT_IS_DELETED", columnDefinition = "bit default 0")
    private boolean isDeleted;
}
