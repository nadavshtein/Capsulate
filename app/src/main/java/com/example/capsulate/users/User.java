package com.example.capsulate.users;

import com.example.capsulate.CoronaStatus;
import com.example.capsulate.UserRole;

public class User {

    public static String userIdConst = "USER_ID";
    protected String fullName;
    protected UserRole role;
    protected String userName;
    protected String password;
    protected String corpId;
    protected String capsuleId;
    protected String previousCapsuleId;
    protected CoronaStatus status;

    public User() {
    }

    public User(String fullName, String userName, String password, UserRole role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.status = CoronaStatus.Healthy; // default
        this.role = role;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public void setStatus(CoronaStatus status) {
        this.status = status;
    }

    public String getCorpId() {
        return corpId;
    }

    public String getCapsuleId() {
        return capsuleId;
    }

    public String getPreviousCapsuleId() {
        return previousCapsuleId;
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

    public UserRole getRole() {
        return role;
    }


}
