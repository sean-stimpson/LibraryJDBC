package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects application to the database.
 * Coded to connect to local mysql DB.
 * You can change the url, username, and password to connect to your own
 * db.
 */
public class DbConnect {
    public static Connection connect() {
        Connection connection;
        String jdbcURL = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root";
        String password = "Programmer123";
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
