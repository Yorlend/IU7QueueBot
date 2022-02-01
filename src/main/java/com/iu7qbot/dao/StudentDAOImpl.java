package com.iu7qbot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iu7qbot.db.QueueDBHandler;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> findStudents() throws SQLException {
        List<Student> res = new ArrayList<>();

        Statement statement = QueueDBHandler.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from students order by id");

        while (rs.next()) {
            int id = rs.getInt("id");
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            int groupId = rs.getInt("group_index");

            res.add(new Student(id, surname, name, groupId));
        }

        return res;
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("insert into students (surname, name, groupId) values ( ?, ?, ? )");
        
        prep.setString(1, student.getSurname());
        prep.setString(2, student.getName());
        prep.setInt(3, student.getGroupId());

        prep.executeUpdate();
    }
}