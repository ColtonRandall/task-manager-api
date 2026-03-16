package com.taskmanagerapi.event;

import org.springframework.context.ApplicationEvent;

public class TaskCreatedEvent extends ApplicationEvent {

    private String id;
    private String name;

    public TaskCreatedEvent(Object source, String id, String name) {
        super(source);
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
