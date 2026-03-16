package com.taskmanagerapi.listener;

import com.taskmanagerapi.event.TaskCompletedEvent;
import com.taskmanagerapi.event.TaskCreatedEvent;
import com.taskmanagerapi.event.TaskDeletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskEventListener {

    private static final Logger log = LoggerFactory.getLogger(TaskEventListener.class);

    @EventListener
    public void onTaskCreated(TaskCreatedEvent event) {
        log.info("Task created: id={}, name={}",
                event.getId(), event.getName());
    }

    @EventListener
    public void onTaskCompleted(TaskCompletedEvent event) {
        log.info("Task completed: id={}, name={}",
                event.getId(), event.getName());
    }

    @EventListener
    public void onTaskDeleted(TaskDeletedEvent event) {
        log.info("Task deleted: id={}", event.getId());
    }
}
