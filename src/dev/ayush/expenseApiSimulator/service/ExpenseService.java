package dev.ayush.expenseApiSimulator.service;

import dev.ayush.expenseApiSimulator.model.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Business logic for expense resources.
 */
public class ExpenseService {
    private List<Expense> expenses; // in-memory expense store

    /**
     * Initialize collections.
     */
    public ExpenseService(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Create expense for a user. @return created expense
     */
    public Expense createExpense(int id, int userId, String category, double amount, LocalDate date, String desc) {
        Expense createdExpense = new Expense(id, userId, category, amount, date, desc);
        expenses.add(createdExpense);
        return createdExpense;
    }

    /**
     * List expenses for a user.
     */
    public List<Expense> listExpensesByUser(int userId) {
        return Collections.unmodifiableList(expenses);
    }

    /**
     * Delete expense by id. @return success flag
     */
    public boolean deleteExpense(int expenseId) {
        for (Expense expense : expenses) {
            if (expense.getId() == expenseId) {
                return expenses.remove(expense);
            }
        }
        return false;
    }

    /**
     * Sum total by user.
     */
    public double totalByUser(int userId) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            if (expense.getUserId() == userId) totalExpense += expense.getAmount();
        }
        return totalExpense;
    }

    /**
     * Sum total by user + category.
     */
    public double totalByUserAndCategory(int userId, String category) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            if (expense.getUserId() == userId && expense.getCategory() == category) totalExpense += expense.getAmount();
        }
        return totalExpense;
    }
}