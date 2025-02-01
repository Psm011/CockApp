package com.book.dao;

import java.sql.*;
import java.util.Scanner;

public class BorrowDAO {
    public static void borrowBook(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter book ID to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String borrower = scanner.nextLine();

        String checkAvailability = "SELECT available FROM books WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(checkAvailability);
        pstmt.setInt(1, bookId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next() && rs.getBoolean("available")) {
            String borrowSql = "INSERT INTO borrowed (book_id, borrower) VALUES (?, ?)";
            String updateBook = "UPDATE books SET available = FALSE WHERE id = ?";
            PreparedStatement borrowStmt = conn.prepareStatement(borrowSql);
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);

            borrowStmt.setInt(1, bookId);
            borrowStmt.setString(2, borrower);
            borrowStmt.executeUpdate();

            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book is not available!");
        }
    }

    public static void returnBook(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();

        String returnSql = "DELETE FROM borrowed WHERE book_id = ?";
        String updateBook = "UPDATE books SET available = TRUE WHERE id = ?";
        PreparedStatement returnStmt = conn.prepareStatement(returnSql);
        PreparedStatement updateStmt = conn.prepareStatement(updateBook);

        returnStmt.setInt(1, bookId);
        updateStmt.setInt(1, bookId);

        int rowsAffected = returnStmt.executeUpdate();
        if (rowsAffected > 0) {
            updateStmt.executeUpdate();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("No record found for this book ID.");
        }
    }
}
