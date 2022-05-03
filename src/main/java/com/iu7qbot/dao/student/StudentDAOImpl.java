package com.iu7qbot.dao.student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iu7qbot.db.QueueDBHandler;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student getStudent(long id) throws SQLException {

        PreparedStatement prep = QueueDBHandler.getConnection()
            .prepareStatement("select name, surname, prom, group from students where id = ?");

        prep.setLong(1, id);
        ResultSet rs = prep.executeQuery();

        if (rs.next()) {
            return new Student(id, rs.getString("surname"), rs.getString("name"), 
                    rs.getInt("prom"), rs.getInt("group"));
        }

        return null;
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        
        PreparedStatement prep = QueueDBHandler.getConnection()
            .prepareStatement("insert into students (id, surname, name, prom, group) values ( ?, ?, ?, ?, ? )");

        prep.setLong(1, student.getId());
        prep.setString(2, student.getSurname());
        prep.setString(3, student.getName());
        prep.setInt(4, student.getProm());
        prep.setInt(5, student.getGroup());

        prep.executeUpdate();
    }
    
}
