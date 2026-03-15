package com.taskmanagerapi.service;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.taskmanagerapi.model.TaskStatus.*;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String name, String description) {
        Task task = new Task(name, description);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(String id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> searchTaskByName(String name) {
        return taskRepository.findTaskByName(name);
    }

    public List<Task> searchTaskByStatus(TaskStatus status) {
        return taskRepository.findTasksByStatus(status);
    }

    @Transactional
    public Optional<Task> completeTask(String id) {
        return taskRepository.findById(id).map(task -> {
            task.setStatus(DONE);
            return task;
        });
    }

    @Transactional
    public Optional<Task> deleteTask(String id) {
        return taskRepository.findById(id).map(task -> {
            if (task.getStatus() == DELETED) {
                logger.info("Task '{}' is already deleted", task.getName());
            } else {
                task.setStatus(DELETED); // soft delete
                logger.info("Task '{}' has been deleted", task.getName());
            }
            return task;
        });
    }

    @Transactional
    public Optional<Task> undoDelete(String id) {
        return taskRepository.findById(id).map(task -> {
            if (task.getStatus() == DELETED) {
                task.setStatus(CREATED);
                logger.info("Task '{}' reactivated", task.getName());
            } else {
                logger.info("Task '{}' could not be reactivated", task.getName());
            }
            return task;
        });
    }
}
