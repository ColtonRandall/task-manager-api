package com.taskmanagerapi;

import com.taskmanagerapi.model.Task;
import com.taskmanagerapi.model.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTests {

    @Test
    void newTask_hasCorrectNameAndStatus() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");

        assertEquals("Buy groceries", task.getName());
        assertEquals(TaskStatus.CREATED, task.getStatus());
    }

    @Test
    void newTask_hasGeneratedId() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");

        assertNotNull(task.getId());
    }

    @Test
    void newTask_hasCreatedAtTimestamp() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");

        assertNotNull(task.getCreatedAt());
    }

    @Test
    void twoTasks_haveUniqueIds() {
        Task task1 = new Task("Task one", "Description one");
        Task task2 = new Task("Task two", "Description two");

        assertNotEquals(task1.getId(), task2.getId());
    }

    @Test
    void completeTask_setsStatusToDone() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        task.setStatus(TaskStatus.DONE);

        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void deleteTask_setsStatusToDeleted() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");
        task.setStatus(TaskStatus.DELETED);

        assertEquals(TaskStatus.DELETED, task.getStatus());
    }

    @Test
    void newTask_hasCorrectDescription() {
        Task task = new Task("Buy groceries", "Milk, eggs, bread");

        assertEquals("Milk, eggs, bread", task.getDescription());
    }
}