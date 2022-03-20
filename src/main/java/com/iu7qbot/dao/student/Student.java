package com.iu7qbot.dao.student;

public class Student {
    private long id;
    private String surname;
    private String name;

    public Student(long id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
