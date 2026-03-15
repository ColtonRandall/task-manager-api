package com.taskmanagerapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String name;
    private String description;
    private LocalDateTime createdAt;

    public Task(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.status = TaskStatus.CREATED;
    }

    // Default constructor for JPA
    protected Task() {
    }

    public String getId() {
        return id;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
