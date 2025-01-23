package com.project.expense;

import java.sql.Date;

public class Expense {
    private int id;
    private double amount;
    private String category;
    private Date date;

    public Expense(int id, double amount, String category, Date date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense [ID: " + id + ", Amount: " + amount + ", Category: " + category + ", Date: " + date + "]";
    }
}
