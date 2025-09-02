package dev.ayush.expenseApiSimulator.model;

import java.time.LocalDate;

/**
 * Represents an expense resource.
 */
public class Expense {
    private int id; // unique expense id
    private int userId; // owning user id
    private String category; // expense category
    private double amount; // expense amount
    private LocalDate date; // expense date
    private String description; // optional description

    /** Constructor: initialize fields. */
    public Expense(int id, int userId, String category, double amount, LocalDate date, String description) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    /** String view for logs and file output. */
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}