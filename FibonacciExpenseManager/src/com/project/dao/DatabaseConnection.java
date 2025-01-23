package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/expense_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "Pranav@123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
