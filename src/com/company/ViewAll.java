package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewAll {
    public void viewAll(Connection connection) {
        String query = "Select * from librarydb.books";
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                String title = result.getString(2);
                String author = result.getString(3);
                String checkedIn = result.getString(4);
                String output = "Book: #%d: %s - %s - %s";
                System.out.println(String.format(output, ++count, title, author, checkedIn));
            }
            if(count == 0){
                System.out.println("Library Empty");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
