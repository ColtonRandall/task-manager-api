package com.taskmanagerapi.controller;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String description = request.get("description");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addTask(name, description));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Task> searchTaskByName(@PathVariable String name) {
        return ResponseEntity.ok(taskService.searchTaskByName(name));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<List<Task>> searchTaskByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.searchTaskByStatus(status));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.completeTask(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> undoDelete(@PathVariable String id) {
        return ResponseEntity.ok(taskService.undoDelete(id));
    }
}
