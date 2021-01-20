package com.owntech.taskmanagement.controller;

import com.owntech.taskmanagement.entities.Task;
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
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable Long id) {
        return taskService.updateTask(task, id);
    }

    @PutMapping("/start-task/{taskId}/{userId}")
    public Task startTask(@PathVariable Long taskId, @PathVariable Long userId) {
        return taskService.startTask(taskId, userId);
    }

    @PutMapping("/complete-task/{taskId}")
    public Task completeTask(@PathVariable Long taskId) {
        return taskService.completeTask(taskId);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
