package dev.ayush.expenseApiSimulator.controller;

import dev.ayush.expenseApiSimulator.service.*;
import dev.ayush.expenseApiSimulator.model.*;

import java.util.*;
import java.time.*;

/**
 * CLI handler for user-like endpoints.
 * "POST /users", "GET /users", "GET /users/{id}", "DELETE /users/{id}"
 */
public class UserController {

    private UserService userService; // service dependency

    /**
     * Constructor injects service.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handle: POST /users (create)
     */
    public void handleCreate(String body) {
        String[] splits = body.split("\\|");
        int id = Integer.parseInt(splits[0]);
        String name = splits[1];
        String email = splits[2];

        User user = userService.createUser(id, name, email);
        System.out.println("201 Created: User " + user.getId());
    } // body format: id|name|email

    /**
     * Handle: GET /users
     */
    public void handleList() {
        List<User> userList = userService.listUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * Handle: GET /users/{id}
     */
    public void handleGet(String idPath) {
        User user = userService.getUser(Integer.parseInt(idPath));
        if (user != null){
            System.out.println("200 OK: " + user);
        }else{
            System.out.println("404 Not Found: User " + idPath);
        }
    } // idPath format: id

    /**
     * Handle: DELETE /users/{id}
     */
    public void handleDelete(String idPath) {
        boolean deleted = userService.deleteUser(Integer.parseInt(idPath));
        if (deleted){
            System.out.println("200 OK: user " + idPath + " deleted.");
        }else{
            System.out.println("404 Not Found: User" + idPath + " not found.");
        }
    }
}