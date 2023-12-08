package com.assignment.twofactor;

public class User {
    private String username;
    private String email;
    private String phoneNumber;

    // Required default constructor for Firebase
    public User() {
        // Default constructor is needed by Firebase
    }

    public User(String username, String email, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
