package com.taskmanagerapi.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private TaskStatus status;

    public Task(String name, String description, LocalDateTime createdAt) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.status = TaskStatus.CREATED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
