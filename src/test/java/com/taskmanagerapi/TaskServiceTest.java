package com.taskmanagerapi;

import com.taskmanagerapi.dto.TaskResponse;
import com.taskmanagerapi.exception.TaskNotFoundException;
import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import com.taskmanagerapi.repository.TaskRepository;
import com.taskmanagerapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private TaskService taskService;

    @Test
    void addTask_returnsTaskResponse() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskResponse response = taskService.addTask("Buy groceries", "Milk, eggs, bread");

        assertEquals("Buy groceries", response.getName());
        assertEquals(TaskStatus.CREATED, response.getStatus());
    }

    @Test
    void getTask_returnsTaskResponse_whenFound() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        TaskResponse response = taskService.getTask(task.getId());

        assertEquals(task.getId(), response.getId());
    }

    @Test
    void getTask_throwsTaskNotFoundException_whenNotFound() {
        when(taskRepository.findById("bad-id")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTask("bad-id"));
    }

    @Test
    void getAllTasks_returnsListOfTaskResponses() {
        List<Task> tasks = List.of(
                new Task("Task one", "Description one"),
                new Task("Task two", "Description two")
        );
        when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskResponse> responses = taskService.getAllTasks();

        assertEquals(2, responses.size());
    }

    @Test
    void completeTask_setsStatusToDone() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        TaskResponse response = taskService.completeTask(task.getId());

        assertEquals(TaskStatus.DONE, response.getStatus());
    }

    @Test
    void deleteTask_setsStatusToDeleted() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        TaskResponse response = taskService.deleteTask(task.getId());

        assertEquals(TaskStatus.DELETED, response.getStatus());
    }

    @Test
    void undoDelete_setsStatusToCreated() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        task.setStatus(TaskStatus.DELETED);
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        TaskResponse response = taskService.undoDelete(task.getId());

        assertEquals(TaskStatus.CREATED, response.getStatus());
    }

    @Test
    void searchTaskByName_returnsTaskResponse_whenFound() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        when(taskRepository.findTaskByName("Buy groceries")).thenReturn(Optional.of(task));

        TaskResponse response = taskService.searchTaskByName("Buy groceries");

        assertEquals("Buy groceries", response.getName());
    }

    @Test
    void searchTaskByName_throwsTaskNotFoundException_whenNotFound() {
        when(taskRepository.findTaskByName("unknown")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.searchTaskByName("unknown"));
    }
}
