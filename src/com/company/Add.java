package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Adds books to library db.
 */
public class Add {
    /**
     * Adds a book to the library.
     * @param connection
     */
    public void add(Connection connection) {
        String author = "";
        String title = "";
        String add = "";
        String sql = "INSERT INTO books (title, author, checkedIn) Values (?, ?, ?)";
        Scanner in = new Scanner(System.in);

        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                boolean adding = true;
                int rows = 0;
                while (adding) {
                    //User Input
                    System.out.println("Please enter the title: ");
                    title = in.nextLine();
                    System.out.println("Please enter the author: ");
                    author = in.nextLine();
                    System.out.println("All books added are checked in by default.");
                    //Setup/execute Statement
                    if (!title.isEmpty() && !author.isEmpty()) {
                        statement.setString(1, title);
                        statement.setString(2, author);
                        statement.setString(3, "1");
                        rows += statement.executeUpdate();
                    } else {
                        System.out.println("Something was wrong with your input");
                        System.out.println("Please try again");
                        continue;
                    }
                    if (rows > 0) {
                        System.out.println("Successful Insert");
                        System.out.println(rows + " row(s) updated.");
                    }
                    //Ask to continue
                    while (adding) {
                        System.out.println("Do you have more to add? Please enter yes or no");
                        add = in.nextLine();
                        if (add.equalsIgnoreCase("yes")) {
                            break;
                        }
                        if (add.equalsIgnoreCase("no")) {
                            adding = false;
                            break;
                        } else {
                            System.out.println("Entry not accepted try again");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
