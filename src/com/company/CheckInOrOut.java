package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * Class has the abilitiy to check in or out books from the db.
 */
public class CheckInOrOut {
    /**
     * Checks books in or out based on parameter input.
     * @param connection
     * @param choice
     */
    public void checkInOrOut (Connection connection, int choice) {
        String title = "";
        String checkedIn;
        String index;
        String select = "";
        String updateTable = "";
        String action = "";
        //Checking out
        if(choice == 1) {
            select = "SELECT * from books WHERE title = ? AND checkedin = 1";
            updateTable = "UPDATE books SET checkedin = 0 WHERE BookIndex =?";
            action = "checked out.";
        }
        //Checking in
        else{
            select = "SELECT * from books WHERE title = ? AND checkedin = 0";
            updateTable = "UPDATE books SET checkedin = 1 WHERE BookIndex =?";
            action = "checked in.";
        }

        try {
            Scanner in = new Scanner(System.in);
            if (connection != null) {
                PreparedStatement query = connection.prepareStatement(select);
                PreparedStatement update = connection.prepareStatement(updateTable);
                boolean checking = true;

                while (checking) {
                    //Grab title from user
                    System.out.println("Please enter the title: ");
                    title = in.nextLine();
                    if (!title.isEmpty()) {
                        query.setString(1, title);
                    } else {
                        System.out.println("Something was wrong with your input");
                        System.out.println("Please try again");
                        continue;
                    }
                    //Return query containing all books with that title.
                    ResultSet result = query.executeQuery();
                    if(result.next()){
                        //Sets query to update first row of books found.
                        checkedIn = result.getString(4);
                        index = result.getString(1);
                        if(checkedIn.equals(checkedIn)){
                            update.setString(1, index);
                            update.executeUpdate();
                            System.out.println("The book is now " + action);
                        }
                        else{
                            System.out.println("Sorry. This book is not available.");
                        }
                        break;
                    }
                    else {
                        System.out.println("Sorry. This book is not available.");
                        break;
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
