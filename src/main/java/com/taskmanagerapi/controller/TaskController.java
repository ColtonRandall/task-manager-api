package com.taskmanagerapi.controller;

import com.taskmanagerapi.dto.TaskRequest;
import com.taskmanagerapi.dto.TaskResponse;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addTask(request.getName(), request.getDescription()));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<TaskResponse> searchTaskByName(@PathVariable String name) {
        return ResponseEntity.ok(taskService.searchTaskByName(name));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<TaskResponse>> searchTaskByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.searchTaskByStatus(status));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.completeTask(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> undoDelete(@PathVariable String id) {
        return ResponseEntity.ok(taskService.undoDelete(id));
    }
}
