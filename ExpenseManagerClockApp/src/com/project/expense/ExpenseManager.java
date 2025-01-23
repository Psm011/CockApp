package com.project.expense;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.project.dao.ExpenseDatabase;



public class ExpenseManager {
    private final ExpenseDatabase database;
    private final Scanner scanner;

    public ExpenseManager() {
        database = new ExpenseDatabase();
        scanner = new Scanner(System.in);
    }

    public void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date date = Date.valueOf(dateStr);

        Expense expense = new Expense(0, amount, category, date);
        database.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    public void editExpense() {
        System.out.print("Enter expense ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter new category: ");
        String category = scanner.nextLine();
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date date = Date.valueOf(dateStr);

        Expense expense = new Expense(id, amount, category, date);
        database.updateExpense(expense);
        System.out.println("Expense updated successfully!");
    }

    public void deleteExpense() {
        System.out.print("Enter expense ID to delete: ");
        int id = scanner.nextInt();
        database.deleteExpense(id);
        System.out.println("Expense deleted successfully!");
    }

    public void viewExpenses() {
        List<Expense> expenses = database.getAllExpenses();
        expenses.forEach(System.out::println);
    }
}
