package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.dto.TaskDto;
import com.owntech.taskmanagement.entities.Task;

import java.util.List;

public interface ITaskService {

    List<TaskDto> getTasks();

    Task getTaskById(Long id);

    TaskDto saveTask(TaskDto taskDto);

    TaskDto updateTask(TaskDto taskDto, Long taskId);

    void deleteTask(Long id);

    TaskDto startTask(Long taskId, Long userId);

    TaskDto completeTask(Long taskId);
}
