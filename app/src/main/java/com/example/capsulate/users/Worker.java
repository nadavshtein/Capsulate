package com.example.capsulate.users;

public class Worker extends User {

    public Worker(String fullName, String userName, String password) {
        super(fullName, userName, password);
        isManager=false;
    }
}
