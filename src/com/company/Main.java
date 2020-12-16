package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * Driver for Library program
 */
public class Main {
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        String answer = "";
        String command = "";
        Connection connection;
        boolean running = true;
        Scanner in = new Scanner(System.in);

        //Connect to db and create library table
        connection = DbConnect.connect();
        CreateLibrary.createLibrary(connection);

        //Create action objects
        Add add = new Add();
        CheckInOrOut checkInOut = new CheckInOrOut();
        Delete delete = new Delete();
        ViewAll viewAll = new ViewAll();

        //Enter into loop of user actions
        while(running) {
            System.out.println("Please type the index of what you would like to do");
            System.out.println("1: Add a book\n2: Delete a book\n3: Checkout a book\n" +
                    "4: Checkin a book\n5: View All Books in library\n6: End Program" );
            command = in.nextLine();
            switch(command) {
                case "1" -> add.add(connection);
                case "2" -> delete.delete(connection);
                case "3" -> checkInOut.checkInOrOut(connection, 1);
                case "4" -> checkInOut.checkInOrOut(connection, 0);
                case "5" -> viewAll.viewAll(connection);
                case "6" -> running = false;
            }
            while(running) {
                System.out.println("Would you like to perform another action? Enter yes or no");
                answer = in.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    break;
                }
                if (answer.equalsIgnoreCase("no")) {
                    running = false;
                    break;
                }
                else {
                    System.out.println("Entry not accepted try again");
                }
            }
        }
        //Delete table and close connection
        CleanUp.cleanUp(connection);
    }
}




