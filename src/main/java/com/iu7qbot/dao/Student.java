package com.iu7qbot.dao;

public class Student {
    private int id;
    private String surname;
    private String name;
    // group_index
    private int groupId;

    public Student(int id, String surname, String name, int groupId) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getGroupId() {
        return groupId;
    }
}