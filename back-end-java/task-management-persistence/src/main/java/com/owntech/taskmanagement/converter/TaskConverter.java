package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.dto.TaskDto;
import com.owntech.taskmanagement.entities.Task;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TaskConverter {

    private TaskConverter() {

    }

    public static TaskDto modelToDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getStartDate(),
                task.getEndDate(), task.getCategory(), task.getUsers());
    }

    public static Task dtoToModel(TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getPriority(),
                taskDto.getStartDate(), taskDto.getEndDate(), taskDto.getCategory(), taskDto.getUsers(), false);
    }

    public static List<TaskDto> modelsToDtos(Collection<Task> tasks) {
        return tasks.stream().filter(Objects::nonNull).map(TaskConverter::modelToDto).collect(Collectors.toList());
    }
}
