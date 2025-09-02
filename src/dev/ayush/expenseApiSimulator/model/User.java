package dev.ayush.expenseApiSimulator.model;

/**
 * Represents a user resource.
 */
public class User {
    private int id; // unique id
    private String name; // user name
    private String email; // user email

    /** Constructor: initialize fields. */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /** String representation for logs and file output. */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}