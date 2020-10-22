package com.example.capsulate.users;

public class Manager extends User {

    public Manager(String fullName, String userName, String password) {
        super(fullName, userName, password);
        isManager=true;
    }
}
