package com.owntech.taskmanagement.controller;

import com.owntech.taskmanagement.converter.TaskConverter;
import com.owntech.taskmanagement.dto.TaskDto;
import com.owntech.taskmanagement.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return TaskConverter.modelToDto(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @PathVariable Long id) {
        return taskService.updateTask(taskDto, id);
    }

    @PutMapping("/start-task/{taskId}/{userId}")
    public TaskDto startTask(@PathVariable Long taskId, @PathVariable Long userId) {
        return taskService.startTask(taskId, userId);
    }

    @PutMapping("/complete-task/{taskId}")
    public TaskDto completeTask(@PathVariable Long taskId) {
        return taskService.completeTask(taskId);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
