package com.example.capsulate.users;

import com.example.capsulate.UserRole;

public class Worker extends User {

    public Worker() {
    }

    public Worker(String fullName, String userName, String password, UserRole role) {
        super(fullName, userName, password, role);
    }
}
