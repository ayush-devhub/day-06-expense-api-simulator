package dev.ayush.expenseApiSimulator;

import dev.ayush.expenseApiSimulator.controller.*;
import dev.ayush.expenseApiSimulator.model.Expense;
import dev.ayush.expenseApiSimulator.model.User;
import dev.ayush.expenseApiSimulator.service.*;
import dev.ayush.expenseApiSimulator.persistence.*;

import java.util.*;

/**
 * Console entry point that parses simple commands and routes to controllers.
 * <p>
 * Commands emulate HTTP: e.g.
 * POST /users 1|Ayush|ayush@example.com
 * GET /users
 * POST /users/1/expenses 101|Food|250.0|2025-08-30|Lunch
 */
public class ApiSimulatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> usersList = new ArrayList<>();
        UserService userService = new UserService(usersList);
        UserController userController = new UserController(userService);
        List<Expense> expenseList = new ArrayList<>();
        ExpenseService expenseService = new ExpenseService(expenseList);
        ExpenseController expenseController = new ExpenseController(expenseService, userService);
        FileService fileService = new FileService();

        System.out.println("===== Expense API Simulator =====\n" +
                "Type a command or 'help' for examples.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            routeCommand(input, userController, expenseController, fileService);
            System.out.println();
        }
    }

    /**
     * Prints the simple CLI help and prompt.
     */
    private static void showHelp() {
        System.out.println("Examples:");
        System.out.println("  POST /users 1|Ayush|ayush@example.com");
        System.out.println("  GET /users");
        System.out.println("  GET /users/1");
        System.out.println("  DELETE /users/1");
        System.out.println("  POST /users/1/expenses 101|Food|250.0|2025-08-30|Lunch");
        System.out.println("  GET /users/1/expenses");
        System.out.println("  SAVE");
        System.out.println("  EXIT");
    }

    /**
     * Read and route one command line.
     */
    private static void routeCommand(String line, UserController uc, ExpenseController ec, FileService fs) {
        if (line.isEmpty()) return;

        if (line.equalsIgnoreCase("help")) showHelp();

        String[] parts = line.split(" ");

        String method = parts[0];
        String path = parts.length > 1 ? parts[1] : "";
        String body = parts.length > 2 ? parts[2] : "";

        if (method.equals("POST") && path.equals("/users")) {
            uc.handleCreate(body);
            return;
        }

        if (method.equals("GET") && path.equals("/users")) {
            uc.handleList();
            return;
        }

        if (method.equals("GET") && path.startsWith("/users/") && !path.contains("expenses")) {
            String idPath = path.substring("/users/".length());
            uc.handleGet(idPath);
            return;
        }

        // DELETE /users/1
        if (method.equals("DELETE") && path.startsWith("/users/")){
            String idPath = path.substring("/users/".length());
            uc.handleDelete(idPath);
        }

        // DELETE /users/1
        if (method.equals("DELETE") && path.startsWith("/users/")){
            String idPath = path.substring("/users/".length());
            uc.handleDelete(idPath);
        }

        // POST /users/1/expenses 101|Food|250.0|2025-08-30|Lunch
        if (method.equals("POST") && path.startsWith("/users/")){
            String userId = path.split("/")[2];
            ec.handleCreateForUser(userId, body);
            return;
        }

        //  System.out.println("  GET /users/1/expenses");
        if (method.equals("GET") && path.startsWith("/users/")){
            ec.handleListForUser(path);
            return;
        }

        if (method.equalsIgnoreCase("SAVE")){
            List<User> users = uc.getUserService().listUsers();
            fs.saveUsers(users, "data/users.txt");

            List<Expense> expenses = ec.getExpenseService().getExpenses();
            fs.saveExpenses(expenses, "data/expense.txt");
            System.out.println("Data saved to data/users.txt and data/expenses.txt");

        }

    }
}