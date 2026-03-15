package com.taskmanagerapi.service;

import com.taskmanagerapi.dto.TaskResponse;
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

    public TaskResponse addTask(String name, String description) {
        Task task = new Task(name, description);
        return TaskResponse.from(taskRepository.save(task));
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskResponse::from)
                .toList();
    }

    public TaskResponse getTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return TaskResponse.from(task);
    }

    public TaskResponse searchTaskByName(String name) {
        Task task = taskRepository.findTaskByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));
        return TaskResponse.from(task);
    }

    public List<TaskResponse> searchTaskByStatus(TaskStatus status) {
        List<Task> tasks = taskRepository.findTasksByStatus(status);
        return tasks.stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional
    public TaskResponse completeTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(DONE);
        return TaskResponse.from(task);
    }

    @Transactional
    public TaskResponse deleteTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(DELETED);
        return TaskResponse.from(task);
    }

    @Transactional
    public TaskResponse undoDelete(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(CREATED);
        return TaskResponse.from(task);
    }
}
