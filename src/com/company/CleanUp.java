package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Deletes table and closes connection.
 */
public class CleanUp {
    /**
     * Deletes table and closes connection.
     * @param connection
     */
    public static void cleanUp(Connection connection){
        String drop = "DROP TABLE books";
        try {
            Statement dropTable = connection.createStatement();
            dropTable.execute(drop);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
