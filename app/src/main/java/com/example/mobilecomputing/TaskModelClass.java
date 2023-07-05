package com.example.mobilecomputing;

public class TaskModelClass {
    Integer taskId;
    String title;
    String description;

    public TaskModelClass(Integer taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

