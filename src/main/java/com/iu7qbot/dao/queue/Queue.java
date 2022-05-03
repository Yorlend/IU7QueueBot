package com.iu7qbot.dao.queue;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class Queue {
    private String task;
    private long id;
    private Timestamp submitted;
    
    public Queue(String task, long id) {
        this.task = task;
        this.id = id;
        this.submitted = Timestamp.from(ZonedDateTime.now(ZoneId.of("Europe/Moscow")).toInstant());
    }

    public Queue(String task, long id, Timestamp submitted) {
        this(task, id);
        this.submitted = submitted;
    }

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", task, id);
    }
}
