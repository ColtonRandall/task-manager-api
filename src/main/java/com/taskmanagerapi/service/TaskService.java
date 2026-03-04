package com.taskmanagerapi.service;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository = new TaskRepository();

    public Task addTask(String name, String description){
        Task task = new Task(name, description);
        taskRepository.save(task);
        return task;
    }

      public List<Task> getAllTasks(){
          return taskRepository.findAllTasks();
      }

    public Optional<Task> getTask(String id) {
        return taskRepository.findTaskById(id);
    }
}
