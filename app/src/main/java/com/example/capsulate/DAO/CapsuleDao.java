package com.example.capsulate.DAO;

import androidx.core.util.Consumer;

import com.example.capsulate.Capsule;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Optional;

public class CapsuleDao implements Dao<Capsule> {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    @Override
    public void get(String id, Consumer<Optional<Capsule>> consumer) {

    }

    @Override
    public void save(Capsule capsule) {

    }

    @Override
    public void delete(Capsule capsule) {

    }
}
