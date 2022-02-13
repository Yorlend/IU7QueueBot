package com.iu7qbot.dao.schedule;

import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iu7qbot.dao.queue.Queue;
import com.iu7qbot.db.QueueDBHandler;

public class ScheduleDAOImpl implements ScheduleDAO {

    @Override
    public List<Queue> getSchedule(String task) throws SQLException {

        List<Queue> res = new ArrayList<>();

        Statement statement = QueueDBHandler.getConnection().createStatement();
        ResultSet rs = statement
            .executeQuery("select q.* from schedule s join queue q on s.task = q.task and " +
            String.format("s.surname = q.surname where s.task = '%s' order by q.submitted", task));

        while (rs.next()) {
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            Timestamp submitted = rs.getTimestamp("submitted");

            res.add(new Queue(task, surname, name, submitted));
        }

        return res;
    }

    @Override
    public void resetSchedule() throws SQLException {

        QueueDBHandler.getConnection().createStatement()
                .executeQuery("truncate schedule");
    }

    @Override
    public void insertSchedule(Queue student) throws SQLException {
        
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("insert into schedule (task, surname) values ( ?, ? )");

        prep.setString(1, student.getTask());
        prep.setString(2, student.getSurname());

        prep.executeUpdate();
    }

    @Override
    public void removeStudent(Queue student) throws SQLException {
        
        PreparedStatement prep = QueueDBHandler.getConnection()
                .prepareStatement("delete from schedule where task = ? and surname = ?");

        prep.setString(1, student.getTask());
        prep.setString(2, student.getSurname());

        prep.executeUpdate();
    }
}
