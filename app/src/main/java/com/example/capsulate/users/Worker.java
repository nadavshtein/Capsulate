package com.example.capsulate.users;

public class Worker extends User {

    public Worker(String fullName, String userName, String password,String corpId) {
        super(fullName, userName, password);
        isManager=false;
    }
}
