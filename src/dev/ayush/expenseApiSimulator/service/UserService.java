package dev.ayush.expenseApiSimulator.service;

import dev.ayush.expenseApiSimulator.model.*;

import java.util.*;

/**
 * Business logic for user resources.
 */
public class UserService {
    private List<User> users; // in-memory user store

    /**
     * Initialize collections.
     */
    public UserService(List<User> users) {
        this.users = users;
    }

    /**
     * Create user. @return created user
     */
    public User createUser(int id, String name, String email) {
        User newUser = new User(id, name, email);
        users.add(newUser);
        return newUser;
    }

    /**
     * Retrieve user by id. @return user or null
     */
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }

        return null;
    }

    /**
     * List all users.
     */
    public List<User> listUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Delete user by id. @return success flag
     */
    public boolean deleteUser(int id) {
        User userToDelete = getUser(id);
        if (users.remove(userToDelete)) return true;
        else return false;
    }
}