package com.taskmanagerapi.service;

import com.taskmanagerapi.model.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final Map<String, Task> taskStore = new HashMap<>();

    public Task addTask(String name, String description){
        Task task = new Task(name, description);
        taskStore.put(task.getId(), task);
        return task;
    }

    public Optional<Task> getTask(String id){
        return Optional.ofNullable(taskStore.get(id));
    }
}
