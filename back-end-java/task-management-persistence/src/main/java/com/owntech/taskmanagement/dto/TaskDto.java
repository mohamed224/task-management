package com.owntech.taskmanagement.dto;

import com.owntech.taskmanagement.entities.Category;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.enums.Priority;
import com.owntech.taskmanagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Category category;
    private Set<User> users;

}
