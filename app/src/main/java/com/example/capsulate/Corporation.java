package com.example.capsulate;

import com.example.capsulate.users.Manager;

import java.util.List;

public class Corporation {

    private String corpId;
    private Manager manager;
    private String name;
    private List<Capsule> capsules;

    public Corporation(String corpId, Manager manager, String name, List<Capsule> capsules) {
        this.corpId = corpId;
        this.manager = manager;
        this.name = name;
        this.capsules = capsules;
    }

    public String getCorpId() {
        return corpId;
    }

    public Manager getManager() {
        return manager;
    }

    public String getName() {
        return name;
    }

    public List<Capsule> getCapsules() {
        return capsules;
    }
}
