package com.example.capsulate.users;

import com.example.capsulate.CoronaStatus;
import com.example.capsulate.Corporation;

public abstract class User {

//    public static String fullNameConstant="FULL_NAME";
//    public static String userNameConstant="USER_NAME";
//    public static String passwordConstant="PASSWORD";
    protected String fullName;
    protected String userName;
    protected String password;
    protected boolean isManager;
    protected Corporation corporation;
    protected CoronaStatus status;

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.status=CoronaStatus.Healthy; // default
    }

    public void setStatus(CoronaStatus status) {
        this.status = status;
    }

    public boolean isManager() {
        return isManager;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public CoronaStatus getStatus() {
        return status;
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
