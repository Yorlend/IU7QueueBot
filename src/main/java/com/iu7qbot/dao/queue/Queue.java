package com.iu7qbot.dao.queue;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class Queue {
    private String task;
    private String surname;
    private String name;
    private Timestamp submitted;
    
    public Queue(String task, String surname, String name) {
        this.task = task;
        this.surname = surname;
        this.name = name;
        this.submitted = Timestamp.from(ZonedDateTime.now(ZoneId.of("Europe/Moscow")).toInstant());
    }

    public Queue(String task, String surname, String name, Timestamp submitted) {
        this(task, surname, name);
        this.submitted = submitted;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    @Override
    public String toString() {
        return String.format("%s %s", surname, name);
    }
}
