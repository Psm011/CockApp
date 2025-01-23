package com.project.main;
import java.util.Scanner;

import com.project.Arithmetic.Calculator;
import com.project.expense.ExpenseManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();
        Calculator calculator = new Calculator();

        while (true) {
            // Display menu for both calculator and expense manager
            System.out.println("\nMain Menu:");
            System.out.println("1. Calculator");
            System.out.println("2. Expense Manager");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Exiting...");
                break; // Exit the program
            }

            switch (choice) {
                case 1: // Calculator
                    System.out.print("Enter first number: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();
                    System.out.println("Addition: " + calculator.add(num1, num2));
                    System.out.println("Subtraction: " + calculator.subtract(num1, num2));
                    System.out.println("Multiplication: " + calculator.multiply(num1, num2));
                    System.out.println("Division: " + calculator.divide(num1, num2));
                    break;
                case 2: // Expense Manager
                    expenseManager.showMenu();
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close(); // Close the scanner
    }
}
