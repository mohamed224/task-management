package com.owntech.taskmanagement.service;

import com.owntech.taskmanagement.dao.TaskDao;
import com.owntech.taskmanagement.dao.UserDao;
import com.owntech.taskmanagement.entities.Task;
import com.owntech.taskmanagement.entities.User;
import com.owntech.taskmanagement.enums.Status;
import com.owntech.taskmanagement.service.impl.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {

    @Mock
    private TaskDao taskDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private TaskService taskService;

    List<Task> tasks = new ArrayList<>();
    Task t1;
    Task t2;
    Task t3;
    Task t4;
    User u;

    @BeforeAll
    void setUp() {
        t1 = new Task("Task1", "Description1", Status.IN_PROGRESS);
        t2 = new Task("Task2", "Description2", Status.IN_PROGRESS);
        t3 = new Task("Task3", "Description3", Status.IN_PROGRESS);
        t4 = new Task("Task3", "Description3", Status.TODO);
        t4.setId(1L);
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        u=new User();
        u.setId(1L);
        u.setUsername("Ellen");
        Set<Task> taskSet = new HashSet<>();
        taskSet.addAll(tasks);
        u.setTasks(taskSet);
         MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTasks() {
        Mockito.when(taskDao.findAll()).thenReturn(tasks);
        Assertions.assertEquals(3, taskService.getTasks().size());
    }

    @Test
    void getTaskById(){
        Mockito.when(taskDao.findById(1L)).thenReturn(Optional.of(t2));
        Assertions.assertEquals(t2,taskService.getTaskById(1L));
    }

    @Test
    void saveTask(){
        Mockito.when(taskDao.save(t1)).thenReturn(t1);
        Assertions.assertEquals("Task1",taskService.saveTask(t1).getTitle());
        Mockito.verify(taskDao, Mockito.times(1)).save(t1);
    }

    @Test
    void updateTask(){
        t1.setTitle("Task 1");
        Mockito.when(taskDao.saveAndFlush(t1)).thenReturn(t1);
        Assertions.assertEquals("Task 1",taskService.updateTask(t1,1L).getTitle());
    }


    @Test
    void deleteTask(){
        t1.setDeleted(true);
        Mockito.when(taskDao.findById(1L)).thenReturn(Optional.of(t1));
        Assertions.assertEquals(true,taskService.getTaskById(1L).isDeleted());
    }

    @Test
    void startTask(){
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.of(u));
        Mockito.when(taskDao.findById(1L)).thenReturn(Optional.of(t4));
        Mockito.when(taskDao.saveAndFlush(t4)).thenReturn(t4);
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> taskService.startTask(1L, 1L));
        String expectedMessage = "You cannot have more than 3 tasks in progress in the same time.";
        Assertions.assertEquals(expectedMessage, runtimeException.getMessage());
        Mockito.verify(taskDao,Mockito.times(0)).findById(1L);
        Mockito.verify(userDao,Mockito.times(1)).findById(1L);
    }

    @Test
    void completeTask(){
        Mockito.when(taskDao.findById(1L)).thenReturn(Optional.of(t2));
        Status status = taskService.completeTask(1L).getStatus();
        Assertions.assertEquals(Status.DONE,status);
    }

}
