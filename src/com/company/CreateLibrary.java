package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates library table
 */
public class CreateLibrary {
    public static void createLibrary(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE books(" +
                    "BookIndex INT NOT NULL AUTO_INCREMENT," +
                    "title VARCHAR (45) NOT NULL, " +
                    "author VARCHAR (45)," +
                    "checkedIn TINYINT CHECK (checkedIn IN(1, 2))," +
                    "PRIMARY KEY (BookIndex, title))";
            statement.execute(query);
            System.out.println("Library Created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
