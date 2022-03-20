package com.iu7qbot.dao.student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iu7qbot.db.QueueDBHandler;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student getStudent(long id) throws SQLException {

        PreparedStatement prep = QueueDBHandler.getConnection()
            .prepareStatement("select (name, surname) from students where id = ?");

        prep.setLong(0, id);
        ResultSet rs = prep.executeQuery();

        if (rs.next()) {
            return new Student(id, rs.getString("surname"), rs.getString("name"));
        }

        return null;
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        
        PreparedStatement prep = QueueDBHandler.getConnection()
            .prepareStatement("insert into students (id, surname, name) values ( ?, ?, ? )");

        prep.setLong(0, student.getId());
        prep.setString(1, student.getSurname());
        prep.setString(2, student.getName());

        prep.executeUpdate();
    }
    
}
