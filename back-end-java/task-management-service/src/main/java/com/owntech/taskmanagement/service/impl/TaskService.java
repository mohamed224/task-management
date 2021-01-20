package com.owntech.taskmanagement.service.impl;

import com.owntech.taskmanagement.dao.TaskDao;
import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.entities.Task;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.enums.Status;
import com.owntech.taskmanagement.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TaskService extends GenericService<Task, Long> implements ITaskService {
    private TaskDao taskDao;
    private UserDao userDao;

    @Autowired
    public TaskService(TaskDao taskDao, UserDao userDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
    }

    @Override
    public List<Task> getTasks() {
        return taskDao.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskDao.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new RuntimeException("Task doesn't exist");
    }

    @Override
    public Task saveTask(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task updateTask(Task task, Long taskId) {
        task.setId(taskId);
        return taskDao.saveAndFlush(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskDao.delete(id);
    }

    @Override
    public Task startTask(Long taskId, Long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent()) {
            Stream<Task> tasksOfTheCurrentUserStream = optionalUser.get().getTasks().stream();
            long numberOfTasksStartedByTheCurrentUser = tasksOfTheCurrentUserStream.filter(task -> task.getStatus().equals(Status.IN_PROGRESS)).count();
            if (numberOfTasksStartedByTheCurrentUser < 3) {
                Task task = getTaskById(taskId);
                task.setStatus(Status.IN_PROGRESS);
                return taskDao.saveAndFlush(task);
            }
            throw new RuntimeException("You cannot have more than 3 tasks in progress in the same time.");
        }
        throw new RuntimeException("User doesn't exist");

    }

    @Override
    public Task completeTask(Long taskId) {
        Optional<Task> optionalTask = taskDao.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(Status.DONE);
            taskDao.saveAndFlush(task);
            return task;
        }
        throw new RuntimeException("Task doesn't exist");
    }
}
