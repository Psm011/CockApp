package com.project.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Expense;

public class ExpenseDao {
    private Connection connection;

    public ExpenseDao() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, expense.getCategory());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getDate());
            stmt.executeUpdate();
        }
    }

    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Expense expense = new Expense(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("date")
                );
                expenses.add(expense);
            }
        }
        return expenses;
    }

    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
