package com.example.capsulate.DAO;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.users.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;

public class ManagerDao implements Dao<Manager> {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    @Override
    public void get(String userName, final Consumer<Optional<Manager>> consumer) {
        dataBase.collection("Users").whereEqualTo("userName", userName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            consumer.accept(Optional.of(document.toObjects(Manager.class).get(0)));
                        } else {
                            consumer.accept(Optional.<Manager>empty());
                        }
                    }
                });
    }

    @Override
    public void save(Manager manager) {
        dataBase.collection("Users").document(manager.getUserName()).set(manager);
    }

    @Override
    public void delete(Manager manager) {
        dataBase.collection("Users").document(manager.getUserName()).delete();
    }
}
