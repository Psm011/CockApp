package com.project.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.project.expense.Expense;

public class ExpenseDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/expense_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "Pranav@123";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addExpense(Expense expense) {
        String query = "INSERT INTO expenses (amount, category, date) VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getCategory());
            statement.setDate(3, expense.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateExpense(Expense expense) {
        String query = "UPDATE expenses SET amount = ?, category = ?, date = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getCategory());
            statement.setDate(3, expense.getDate());
            statement.setInt(4, expense.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExpense(int id) {
        String query = "DELETE FROM expenses WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                String category = resultSet.getString("category");
                Date date = resultSet.getDate("date");
                expenses.add(new Expense(id, amount, category, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}
