package com.taskmanagerapi.service;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.taskmanagerapi.model.TaskStatus.DELETED;
import static com.taskmanagerapi.model.TaskStatus.DONE;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    TaskRepository taskRepository = new TaskRepository();

    public Task addTask(String name, String description) {
        Task task = new Task(name, description);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    public Optional<Task> getTask(String id) {
        return taskRepository.findTaskById(id);
    }

    public Optional<Task> completeTask(String id) {
        return taskRepository.findTaskById(id).map(task -> {
            task.setStatus(DONE);
            taskRepository.save(task);
            return task;
        });
    }

    public Optional<Task> deleteTask(String id) {
        return taskRepository.findTaskById(id).map(task -> {
            if (task.getStatus() == DELETED) {
                logger.info("Task {} is already deleted", task.getName());
            } else {
                task.setStatus(DELETED); // soft delete
                taskRepository.save(task);
                logger.info("Task {} marked as DELETED", task.getName());
            }
            return task;
        });
    }
}
