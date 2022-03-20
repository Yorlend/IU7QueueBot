package com.iu7qbot.dao.student;

import java.sql.SQLException;

public interface StudentDAO {
    
    Student getStudent(long id)
        throws SQLException;
    
    void insertStudent(Student student)
        throws SQLException;
}
