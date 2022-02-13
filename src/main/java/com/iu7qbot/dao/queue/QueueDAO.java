package com.iu7qbot.dao.queue;

import java.sql.SQLException;
import java.util.List;

public interface QueueDAO {

    public List<Queue> findStudents()
        throws SQLException;

    public List<Queue> findStudents(String task)
        throws SQLException;
        
    public void insertStudent(Queue student)
        throws SQLException;

    boolean exists(Queue student)
        throws SQLException;

    public void removeStudent(Queue student)
        throws SQLException;
}