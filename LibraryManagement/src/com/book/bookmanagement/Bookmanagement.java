package com.book.bookmanagement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.book.dao.BookDAO;
import com.book.dao.BorrowDAO;
import com.book.dao.DatabaseConnection;

public class Bookmanagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DatabaseConnection.getConnection();
            BookDAO.createTables(conn);

            while (true) {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        BookDAO.addBook(conn, scanner);
                        break;
                    case 2:
                        BookDAO.viewBooks(conn);
                        break;
                    case 3:
                        BorrowDAO.borrowBook(conn, scanner);
                        break;
                    case 4:
                        BorrowDAO.returnBook(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}