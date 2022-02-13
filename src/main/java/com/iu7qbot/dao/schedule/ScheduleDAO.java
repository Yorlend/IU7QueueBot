package com.iu7qbot.dao.schedule;

import java.sql.SQLException;
import java.util.List;

import com.iu7qbot.dao.queue.Queue;

public interface ScheduleDAO {
    List<Queue> getSchedule(String task)
        throws SQLException;

    void resetSchedule()
        throws SQLException;

    void removeStudent(Queue student)
        throws SQLException;

    void insertSchedule(Queue student)
        throws SQLException;
}
