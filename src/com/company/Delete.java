package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Deletes books out of db library.
 */
public class Delete {
    /**
     * Deletes books out of library.
     * @param connection
     */
    public void delete(Connection connection){
        Scanner in = new Scanner(System.in);
        String title;
        String answer;
        int count;
        int deleted = 0;
        String query = "SELECT COUNT(*) FROM books WHERE Title = ?";
        String delete = "DELETE FROM books WHERE Title =? ORDER BY Title asc LIMIT ?";
        //Enter into deletion.
        try {
            PreparedStatement findBook = connection.prepareStatement(query);
            PreparedStatement deleteBook = connection.prepareStatement(delete);
            if (connection != null) {
                boolean deleting = true;
                while (deleting) {
                    //User inputs create statements
                    System.out.println("Please enter the title of the book you wish to be deleted.");
                    title = in.nextLine();
                    findBook.setString(1, title);
                    deleteBook.setString(1, title);
                    ResultSet result = findBook.executeQuery();
                    //If we get a result we enter this statement.
                    if (result.next()) {
                        count = Integer.parseInt(result.getString(1));
                        if (count == 0) {
                            System.out.println("The book was not found.");
                            break;
                        } else if (count > 0) {
                            System.out.println(count + " copies found");
                            System.out.println("Would you like to delete all copies or only one?");
                            while (true) {
                                System.out.println("Enter One for one copies or All for all copies");
                                answer = in.nextLine();
                                if (answer.equalsIgnoreCase("all")) {
                                    deleteBook.setInt(2,99999);
                                    break;
                                }
                                else if(answer.equalsIgnoreCase("one")){
                                    deleteBook.setInt(2,1);
                                    break;
                                }
                                else{
                                    System.out.println("Please try again");
                                }
                            }
                        }
                        deleted = deleteBook.executeUpdate();
                        if (deleted > 0) {
                            System.out.println("Book deleted!");
                        }
                        System.out.println("Would you like to delete more? Enter yes or no");
                        answer = in.nextLine();
                        while (deleting) {
                            if (answer.equalsIgnoreCase("yes")) {
                                break;
                            }
                            if (answer.equalsIgnoreCase("no")) {
                                deleting = false;
                                break;
                            }
                        }
                    }

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

