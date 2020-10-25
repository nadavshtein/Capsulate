package com.example.capsulate.DAO;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.Capsule;
import com.example.capsulate.Corporation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;

public class CapsuleDao implements Dao<Capsule> {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    @Override
    public void get(String corpId, Consumer<Optional<Capsule>> consumer) {

    }

    @Override
    public void save(Capsule capsule) {

    }

    @Override
    public void delete(Capsule capsule) {

    }

    public void addNewCapsule(String corpId, Capsule capsule) {

        dataBase.collection("Corps").document(corpId).collection("Capsules")
                .document(capsule.getCapsuleId()).set(capsule);
    }

    public void getCapsuleById(String corpId, String capsuleId, final Consumer<Optional<Capsule>> consumer) {
        dataBase.collection("Corps").document(corpId).collection("Capsules")
                .whereEqualTo("capsuleId", capsuleId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            consumer.accept(Optional.of(document.toObjects(Capsule.class).get(0)));
                        } else {
                            consumer.accept(Optional.<Capsule>empty());
                        }
                    }
                });

    }
}