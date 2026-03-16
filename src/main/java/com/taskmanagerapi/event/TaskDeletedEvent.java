package com.taskmanagerapi.event;

import org.springframework.context.ApplicationEvent;

public class TaskDeletedEvent extends ApplicationEvent {

    private String id;

    public TaskDeletedEvent(Object source, String id) {
        super(source);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
