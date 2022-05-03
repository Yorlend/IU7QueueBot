package com.iu7qbot.dao.schedule;

public class Schedule {
    private String task;
    private long id;

    public Schedule(String task, long id) {
        this.task = task;
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public long getId() {
        return id;
    }
}
