package dev.ayush.expenseApiSimulator.persistence;

import dev.ayush.expenseApiSimulator.model.*;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

/**
 * Simple file persistence for users & expenses (text files).
 */
public class FileService {

    /**
     * Ensure data directory and files exist.
     */
//    public void ensureDataFiles() { ...}

    /**
     * Save users to data/users.txt
     */
    public void saveUsers(List<User> users, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (User user : users) {
                bufferedWriter.write(user.getId() + "," + user.getName() + "," + user.getEmail());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save expenses to data/expenses.txt
     */
    public void saveExpenses(List<Expense> expenses, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (Expense expense : expenses) {
                bufferedWriter.write(expense.getId() + "," + expense.getUserId() + "," + expense.getCategory() + "," + expense.getAmount() + "," + expense.getDate() + "," + expense.getDescription());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load users from file. @return list of users
     */
    public List<User> loadUsers(String path) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splits = line.split(",");
                int id = Integer.parseInt(splits[0]);
                String name = splits[1];
                String email = splits[2];
                userList.add(new User(id, name, email));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * Load expenses from file. @return list of expenses
     */
    public List<Expense> loadExpenses(String path) {
        List<Expense> expenseList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splits = line.split(",");
                int id = Integer.parseInt(splits[0]);
                int userId = Integer.parseInt(splits[1]);
                String category = splits[2];
                double amount = Double.parseDouble(splits[3]);
                LocalDate date = LocalDate.parse(splits[4]);
                String description = splits[5];

                expenseList.add(new Expense(id, userId, category, amount, date, description));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return expenseList;
    }
}