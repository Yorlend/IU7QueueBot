package com.iu7qbot.dao.queue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.iu7qbot.db.QueueDBHandler;

public class QueueDAOImpl implements QueueDAO {

    @Override
    public List<Queue> findStudents() throws SQLException {
        List<Queue> res = new ArrayList<>();

        Statement statement = QueueDBHandler.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from queue order by submitted");

        while (rs.next()) {
            String task = rs.getString("task");
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            Timestamp submitted = rs.getTimestamp("submitted");

            res.add(new Queue(task, surname, name, submitted));
        }

        return res;
    }

    @Override
    public void insertStudent(Queue student) throws SQLException {
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("insert into queue (task, surname, name) values ( ?, ?, ? )");
        
        prep.setString(1, student.getTask());
        prep.setString(2, student.getSurname());
        prep.setString(3, student.getName());

        prep.executeUpdate();
    }

    @Override
    public List<Queue> findStudents(String task) throws SQLException {
        List<Queue> res = new ArrayList<>();

        Statement statement = QueueDBHandler.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(String
                .format("select * from queue where task = '%s' order by submitted", task));

        while (rs.next()) {
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            Timestamp submitted = rs.getTimestamp("submitted");

            res.add(new Queue(task, surname, name, submitted));
        }

        return res;
    }

    @Override
    public void removeStudent(Queue student) throws SQLException {
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("delete from queue where task = ? and surname = ?");

        prep.setString(1, student.getTask());
        prep.setString(2, student.getSurname());

        prep.executeUpdate();
    }
}