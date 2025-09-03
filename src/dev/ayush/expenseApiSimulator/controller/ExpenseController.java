package dev.ayush.expenseApiSimulator.controller;

import dev.ayush.expenseApiSimulator.service.*;
import dev.ayush.expenseApiSimulator.model.*;

import java.util.*;
import java.time.*;

/**
 * CLI handler for expense-like endpoints.
 * "POST /users/{id}/expenses", "GET /users/{id}/expenses", "DELETE /expenses/{id}", "GET /users/{id}/reports"
 */
public class ExpenseController {

    private ExpenseService expenseService;
    private UserService userService;

    /**
     * Constructor injects services.
     */
    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    /**
     * Handle: POST /users/{id}/expenses (body: expenseId|category|amount|date|desc)
     */
    public void handleCreateForUser(String userIdPath, String body) {
        String[] splits = body.split("\\|");

        int id = Integer.parseInt(splits[0]); // unique expense id
        String category = splits[1]; // expense category
        double amount = Double.parseDouble(splits[2]); // expense amount
        LocalDate date = LocalDate.parse(splits[3]); // expense date
        String description = splits[4];

        int userId = Integer.parseInt(userIdPath); // owning user id

        expenseService.createExpense(userId, id, category, amount, date, description);
        System.out.println("201 Created: Expense " + id + " for user " + userId);

    }

    /**
     * Handle: GET /users/{id}/expenses
     */
    public void handleListForUser(String userIdPath) {
        String[] idPath = userIdPath.split("/");
        int userId = Integer.parseInt(idPath[2]);

        List<Expense> expenseList = expenseService.listExpensesByUser(userId);
        System.out.println("200 OK");
        for (Expense expense : expenseList) {
            System.out.println(expense);
        }
    }

    /**
     * Handle: DELETE /expenses/{id}
     */
    public void handleDeleteExpense(String expenseIdPath) {
        String[] expenseIdSplits = expenseIdPath.split("/");
        int expenseId = Integer.parseInt(expenseIdSplits[2]);

        if (expenseService.deleteExpense(expenseId)) {
            System.out.println("200 OK: Expense id " + expenseId + " deleted.");
        } else {
            System.out.println("404 Not Found: Expense id " + expenseId + " not found.");
        }

    }

    public ExpenseService getExpenseService() {
        return expenseService;
    }

    /**
     * Handle: GET /users/{id}/reports?category=Food
     */
//    public void handleReports(String userIdPath, Map<String, String> queryParams) { ...}
}