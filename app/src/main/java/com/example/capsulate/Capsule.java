package com.example.capsulate;

import android.graphics.Color;

import com.example.capsulate.users.User;

import java.util.List;

public class Capsule {

    private String capsuleId;
    private Color color;
    private CoronaStatus status;
    private List<User> employees;

    public Capsule(String capsuleId, Color color, CoronaStatus status, List<User> employees) {
        this.capsuleId = capsuleId;
        this.color = color;
        this.status = status;
        this.employees = employees;
    }

    public String getCapsuleId() {
        return capsuleId;
    }

    public Color getColor() {
        return color;
    }

    public CoronaStatus getStatus() {
        return status;
    }

    public List<User> getEmployees() {
        return employees;
    }
}
