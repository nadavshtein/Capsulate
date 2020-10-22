package com.example.capsulate;

import com.example.capsulate.users.Manager;

import java.util.ArrayList;
import java.util.List;

public class Corporation {

    public static String corpIdConst="CORP_ID";

    private String corpId;
    private String name;
    private List<String> capsules;
    private List<String> employees;

    public  Corporation(){};

    public Corporation(String corpId, String name) {
        this.corpId = corpId;
        this.name = name;
        this.employees = new ArrayList<>();
        this.capsules = new ArrayList<>();
    }


    public String getCorpId() {
        return corpId;
    }

    public String getName() {
        return name;
    }

    public List<String> getCapsules() {
        List<String> copy=new ArrayList<>();
        copy.addAll(capsules);
        return copy;
    }

    public List<String> getEmployees() {
        List<String> copy=new ArrayList<>();
        copy.addAll(employees);
        return copy;
    }

    public void addEmployee(String userId) {
        this.employees.add(userId);
    }
}
