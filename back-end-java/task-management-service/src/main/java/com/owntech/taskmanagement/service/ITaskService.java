package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.entities.Task;

import java.util.List;

public interface ITaskService {

    List<Task> getTasks();

    Task getTaskById(Long id);

    Task saveCategory(Task task);

    Task updateTask(Task task, Long taskId);

    void deleteTask(Long id);

    Task startTask(Long taskId, Long userId);

    Task completeTask(Long taskId);
}
