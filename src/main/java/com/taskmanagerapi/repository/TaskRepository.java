package com.taskmanagerapi.repository;

import com.taskmanagerapi.model.Task;

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

    public void delete(String id){
        taskStore.remove(id);
    }
}
