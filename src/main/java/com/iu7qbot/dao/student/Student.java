package com.iu7qbot.dao.student;

import java.util.Calendar;

public class Student {
    private long id;
    private String surname;
    private String name;
    private int prom;
    private int group;

    public Student(long id, String surname, String name, int prom, int group) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.prom = prom;
        this.group = group;
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

    public int getProm() {
        return prom;
    }

    public int getGroup() {
        return group;
    }

    public int getFullGroup() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        int sem = (prom - year) * 2;

        if (month > 8)
            sem++;
        else if (month < 2)
            sem--;

        return sem * 10 + group;
    }

    @Override
    public String toString() {
        return String.format("%s %s - ИУ7-%dБ", surname, name, getFullGroup());
    }
}
