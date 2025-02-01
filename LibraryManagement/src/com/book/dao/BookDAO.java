package com.book.dao;

import java.sql.*;
import java.util.Scanner;

public class BookDAO {
    public static void createTables(Connection conn) throws SQLException {
        String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255) NOT NULL, " +
                "author VARCHAR(255) NOT NULL, " +
                "available BOOLEAN DEFAULT TRUE)";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(createBooksTable);
    }

    public static void addBook(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, author);
        pstmt.executeUpdate();

        System.out.println("Book added successfully!");
    }

    public static void viewBooks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM books WHERE available = TRUE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nAvailable Books:");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ". " + rs.getString("title") + " by " + rs.getString("author"));
        }
    }
}
