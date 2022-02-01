package com.iu7qbot.dao;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    public List<Student> findStudents()
        throws SQLException;
        
    public void insertStudent(Student student)
        throws SQLException;
}