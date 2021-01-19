package com.owntech.taskmanagement.dao;

import com.owntech.taskmanagement.entities.Task;
import com.owntech.taskmanagement.enums.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends BaseDao<Task, Long> {
    List<Task> findByStatusAndIsDeletedFalse(Status status);
}
