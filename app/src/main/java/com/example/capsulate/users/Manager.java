package com.example.capsulate.users;

import com.example.capsulate.UserRole;

public class Manager extends User {

    public Manager() {
    }

    public Manager(String fullName, String userName, String password, UserRole role) {
        super(fullName, userName, password, role);
    }
}
