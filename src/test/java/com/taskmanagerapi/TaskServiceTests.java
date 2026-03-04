package com.taskmanagerapi;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.service.TaskService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTests {

    private final TaskService taskService = new TaskService();

    @Test
    void addTask_returnsTaskWithCorrectNameAndStatus() {
        Task result = taskService.addTask("create test class", "TaskService tests");

        assertEquals("create test class", result.getName());
        assertEquals(TaskStatus.CREATED, result.getStatus());
    }

    @Test
    void getTask_returnsTaskForValidId() {
        Task created = taskService.addTask("Buy groceries", "Milk, eggs, bread");

        Optional<Task> result = taskService.getTask(created.getId());

        assertTrue(result.isPresent());
        assertEquals(created.getId(), result.get().getId());
    }

    @Test
    void getTask_returnsEmptyForUnknownId() {
        Optional<Task> result = taskService.getTask("non-existent-id");

        assertTrue(result.isEmpty());
    }
}