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
            long id = rs.getLong("id");
            Timestamp submitted = rs.getTimestamp("submitted");

            res.add(new Queue(task, id, submitted));
        }

        return res;
    }

    @Override
    public List<Queue> findStudents(int fullGroup) throws SQLException {
        return new ArrayList<Queue>();
    }

    @Override
    public void insertStudent(Queue student) throws SQLException {
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("insert into queue (task, id, submitted) values (?, ?, ?)");
        
        prep.setString(1, student.getTask());
        prep.setLong(2, student.getId());
        prep.setTimestamp(3, student.getSubmitted());;

        prep.executeUpdate();
    }

    @Override
    public List<Queue> findStudents(String task) throws SQLException {
        List<Queue> res = new ArrayList<>();

        Statement statement = QueueDBHandler.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(String
                .format("select * from queue where task = '%s' order by submitted", task));

        while (rs.next()) {
            long id = rs.getLong("id");
            Timestamp submitted = rs.getTimestamp("submitted");

            res.add(new Queue(task, id, submitted));
        }

        return res;
    }

    @Override
    public List<Queue> findStudents(String task, int fullGroup) throws SQLException {
        return new ArrayList<Queue>();
    }

    @Override
    public void removeStudent(Queue student) throws SQLException {
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("delete from queue where task = ? and id = ?");

        prep.setString(1, student.getTask());
        prep.setLong(2, student.getId());

        prep.executeUpdate();
    }

    @Override
    public boolean exists(Queue student) throws SQLException {
        
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("select * from queue where task = ? and id = ?");

        prep.setString(1, student.getTask());
        prep.setLong(2, student.getId());

        ResultSet rs = prep.executeQuery();

        if (rs.next()) {
            return true;
        }
        
        return false;
    }
}