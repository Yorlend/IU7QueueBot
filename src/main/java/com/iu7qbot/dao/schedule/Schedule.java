package com.iu7qbot.dao.schedule;

public class Schedule {
    private String task;
    private String surname;


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Schedule(String task, String surname) {
        this.task = task;
        this.surname = surname;
    }
}
