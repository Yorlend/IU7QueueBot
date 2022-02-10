package com.iu7qbot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueueDBHandler {

    private static Connection conn = null;

    static {
        String url = System.getenv("JDBC_DATABASE_URL");
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
