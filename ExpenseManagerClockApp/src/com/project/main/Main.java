package com.project.main;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import com.project.clock.Clock;
import com.project.expense.ExpenseManager;

public class Main {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Show the main menu
            System.out.println("\nMain Menu:");
            System.out.println("1. Show Digital Clock");
            System.out.println("2. Add Expense");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. View Expenses");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show the digital clock in a new window
                    SwingUtilities.invokeLater(() -> {
                        Clock clock = new Clock();
                        clock.setVisible(true);  // Make the clock visible in its own window
                    });
                    break;

                case 2:
                    expenseManager.addExpense();
                    break;

                case 3:
                    expenseManager.editExpense();
                    break;

                case 4:
                    expenseManager.deleteExpense();
                    break;

                case 5:
                    expenseManager.viewExpenses();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;  // Exit the program

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}