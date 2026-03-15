package com.taskmanagerapi.controller;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            String description = request.get("description");

            Task task = taskService.addTask(name, description);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        return taskService.getTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks/search/{name}")
    public ResponseEntity<Task> searchTaskByName(@PathVariable String name) {
        return taskService.searchTaskByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks/search/status/{status}")
    public ResponseEntity<List<Task>> searchTaskByStatus(@PathVariable TaskStatus status) {
        List<Task> tasks = taskService.searchTaskByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable String id) {
        return taskService.completeTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> undoDelete(@PathVariable String id) {
        return taskService.undoDelete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
