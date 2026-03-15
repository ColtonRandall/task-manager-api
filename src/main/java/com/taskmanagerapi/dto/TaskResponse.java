package com.taskmanagerapi.dto;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponse {

    private String id;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;

    public TaskResponse(String id, String name, String description, TaskStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    /*
        factory method that converts a Task entity into a TaskResponse DTO.
     */
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
