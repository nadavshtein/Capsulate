package com.example.capsulate.users;

public abstract class User {

    protected String fullName;
    protected String userName;
    protected String password;

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


}
