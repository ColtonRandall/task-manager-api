package com.taskmanagerapi.controller;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> request){
        try
        {
            String name = request.get("name");
            String description = request.get("description");

            Task task = taskService.addTask(name, description);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id){
        return taskService.getTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
        return ResponseEntity.notFound().build();
    }
}
