package com.example.capsulate.users;

public class Worker extends User {

    private String corpCode;

    public Worker(String fullName, String userName, String password, String corpCode) {
        super(fullName, userName, password);
        this.corpCode=corpCode;
    }

    public String getCorpCode() {
        return corpCode;
    }
}
