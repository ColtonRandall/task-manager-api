package com.taskmanagerapi.controller;

import com.taskmanagerapi.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class TaskController {

    private final Map<String, Task> taskStore = new HashMap<>();

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> request){
        try
        {
            String name = request.get("name");
            String description = request.get("description");

            Task task = new Task(name, description, LocalDateTime.now());
            taskStore.put(task.getId(), task);

            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id){
        return Optional.ofNullable(taskStore.get(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
