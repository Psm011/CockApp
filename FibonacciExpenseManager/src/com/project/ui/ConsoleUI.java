package com.project.ui;

import java.util.List;
import java.util.Scanner;

import com.project.dao.ExpenseDao;
import com.project.fibonacci.FibonacciGenerator;
import com.project.model.Expense;

public class ConsoleUI {
    private final FibonacciGenerator fibonacciGenerator = new FibonacciGenerator();
    private final ExpenseDao expenseDao = new ExpenseDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Generate Fibonacci Series");
            System.out.println("2. Add Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1 -> generateFibonacci();
                    case 2 -> addExpense();
                    case 3 -> viewExpenses();
                    case 4 -> deleteExpense();
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void generateFibonacci() {
        System.out.print("Enter the number of terms: ");
        int terms = scanner.nextInt();
        List<Integer> fibonacciSeries = fibonacciGenerator.generate(terms);
        System.out.println("Fibonacci Series: " + fibonacciSeries);
    }

    private void addExpense() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Expense expense = new Expense(0, category, amount, date);
        try {
            expenseDao.addExpense(expense);
            System.out.println("Expense added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    private void viewExpenses() {
        try {
            List<Expense> expenses = expenseDao.getAllExpenses();
            System.out.println("Expenses:");
            System.out.printf("%-5s %-15s %-10s %-12s%n", "ID", "Category", "Amount", "Date"); // Properly aligned headers
            System.out.println("-----------------------------------------------------");
            for (Expense expense : expenses) {
                System.out.printf("%-5d %-15s %-10.2f %-12s%n",
                    expense.getId(),
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getDate());
            }
        } catch (Exception e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
        }
    }

    private void deleteExpense() {
        System.out.print("Enter expense ID to delete: ");
        int id = scanner.nextInt();
        try {
            expenseDao.deleteExpense(id);
            System.out.println("Expense deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting expense: " + e.getMessage());
        }
    }
}
