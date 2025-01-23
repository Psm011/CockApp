package com.project.report;

import java.util.List;

import com.project.expense.Expense;

public class ExpenseReport {
    public void generateReport(List<Expense> expenses) {
        double total = 0;
        System.out.println("Expense Report:");
        for (Expense expense : expenses) {
            System.out.println(expense);
            total += expense.getAmount();
        }
        System.out.println("\nTotal Expenses: $" + total);
    }
}
