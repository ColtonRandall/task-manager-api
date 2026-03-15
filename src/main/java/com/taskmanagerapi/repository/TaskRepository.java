package com.taskmanagerapi.repository;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findTaskByName(String name);

    List<Task> findTasksByStatus(TaskStatus status);
}