package com.project.expense;
import java.util.*;

import com.project.dao.ExpenseDatabase;
import com.project.report.ExpenseReport;
import java.sql.Date;

public class ExpenseManager {
    private final ExpenseDatabase expenseDatabase;
    private final Scanner scanner;

    public ExpenseManager() {
        expenseDatabase = new ExpenseDatabase();
        scanner = new Scanner(System.in);
    }

    public void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date date = Date.valueOf(dateStr);  // Using java.sql.Date for conversion

        Expense expense = new Expense(0, amount, category, date);
        expenseDatabase.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    public void editExpense() {
        System.out.print("Enter expense ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter new amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline
        System.out.print("Enter new category: ");
        String category = scanner.nextLine();
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date date = Date.valueOf(dateStr);  // Using java.sql.Date for conversion

        Expense expense = new Expense(id, amount, category, date);
        expenseDatabase.updateExpense(expense);
        System.out.println("Expense updated successfully!");
    }

    public void deleteExpense() {
        System.out.print("Enter expense ID to delete: ");
        int id = scanner.nextInt();
        expenseDatabase.deleteExpense(id);
        System.out.println("Expense deleted successfully!");
    }

    public void viewExpenses() {
        List<Expense> expenses = expenseDatabase.getAllExpenses();
        ExpenseReport report = new ExpenseReport();
        report.generateReport(expenses);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nExpense Manager Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View Expenses");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    editExpense();
                    break;
                case 3:
                    deleteExpense();
                    break;
                case 4:
                    viewExpenses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
