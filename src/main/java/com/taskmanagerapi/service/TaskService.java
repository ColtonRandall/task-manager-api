package com.taskmanagerapi.service;

import com.taskmanagerapi.exception.TaskNotFoundException;
import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.taskmanagerapi.model.TaskStatus.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String name, String description) {
        Task task = new Task(name, description);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task searchTaskByName(String name) {
        return taskRepository.findTaskByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));
    }

    public List<Task> searchTaskByStatus(TaskStatus status) {
        return taskRepository.findTasksByStatus(status);
    }

    @Transactional
    public Task completeTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(DONE);
        return task;
    }

    @Transactional
    public Task deleteTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(DELETED);
        return task;
    }

    @Transactional
    public Task undoDelete(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setStatus(CREATED);
        return task;
    }
}
