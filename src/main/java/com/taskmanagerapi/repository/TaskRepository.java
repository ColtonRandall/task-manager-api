package com.taskmanagerapi.repository;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository {

    private final Map<String, Task> taskStore = new HashMap<>();

    public void save(Task task) {
        taskStore.put(task.getId(), task);
    }

   public List<Task> findAllTasks() {
       return new ArrayList<>(taskStore.values());
   }

    public Optional<Task> findTaskById(String id) {
        return Optional.ofNullable(taskStore.get(id));
    }

    public Optional<Task> findTaskByName(String name){
        return taskStore.values().stream()
            .filter(task -> task.getName().equals(name))
            .findFirst();
    }

    public List<Task> findTasksByStatus(TaskStatus status) {
        return taskStore.values().stream()
                .filter(task -> status.equals(task.getStatus())) // "status" first to avoid NullPointer
                .toList();
    }


    public void delete(String id){
        taskStore.remove(id);
    }
}
