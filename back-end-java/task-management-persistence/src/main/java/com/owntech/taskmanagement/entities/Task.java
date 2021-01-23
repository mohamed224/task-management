package com.owntech.taskmanagement.entities;

import com.owntech.taskmanagement.audit.Auditable;
import com.owntech.taskmanagement.converter.PriorityConverter;
import com.owntech.taskmanagement.converter.StatusConverter;
import com.owntech.taskmanagement.enums.Priority;
import com.owntech.taskmanagement.enums.Status;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "T_TASK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"isDeleted"})
@EntityListeners(AuditingEntityListener.class)
public class Task extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TSK_ID")
    private Long id;
    @Column(name = "TSK_TITLE")
    private String title;
    @Column(name = "TSK_DESCRIPTION")
    private String description;
    @Column(name = "TSK_STATUS")
    @Convert(converter = StatusConverter.class)
    private Status status;
    @Column(name = "TSK_PRIORITY")
    @Convert(converter = PriorityConverter.class)
    private Priority priority;
    @Column(name = "TSK_START_DATE")
    private LocalDateTime startDate;
    @Column(name = "TSK_END_DATE")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "TSK_CATEGORY")
    private Category category;
    @ManyToMany
    @JoinTable(name = "TJ_TSK_USER", joinColumns = {
            @JoinColumn(name = "TSK_ID", referencedColumnName = "TSK_ID")
    }, inverseJoinColumns = {@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")})
    private Set<User> users;
    @Column(name = "TSK_IS_DELETED", columnDefinition = "bit default 0")
    private boolean isDeleted;

    public Task(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
