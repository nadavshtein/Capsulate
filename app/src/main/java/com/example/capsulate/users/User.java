package com.example.capsulate.users;

import com.example.capsulate.Capsule;
import com.example.capsulate.CoronaStatus;

public abstract class User {

    public static String userIdConst="USER_ID";
    protected String fullName;
    protected String userName;
    protected String password;
    protected boolean isManager;
    protected String corpId;
    protected String capsuleId;
    protected Capsule previousCapsuleId;
    protected CoronaStatus status;

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.status=CoronaStatus.Healthy; // default
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public void setStatus(CoronaStatus status) {
        this.status = status;
    }

    public boolean isManager() {
        return isManager;
    }

    public String getCorpId() {
        return corpId;
    }

    public String getCapsuleId() {
        return capsuleId;
    }

    public Capsule getPreviousCapsuleId() {
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


}
