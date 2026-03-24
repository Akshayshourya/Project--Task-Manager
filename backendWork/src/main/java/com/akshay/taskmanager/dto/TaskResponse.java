package com.akshay.taskmanager.dto;

public class TaskResponse {
    private Long id;

    private String title , description;

    public TaskResponse(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    private boolean completed;

    public Long getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
