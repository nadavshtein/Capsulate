package com.example.capsulate.DAO;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.Corporation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;

public class CorporationDao implements Dao<Corporation> {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    @Override
    public void get(String corpId, final Consumer<Optional<Corporation>> consumer) {
        dataBase.collection("Corps").whereEqualTo("corpId", corpId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            consumer.accept(Optional.of(document.toObjects(Corporation.class).get(0)));
                        } else {
                            consumer.accept(Optional.<Corporation>empty());
                        }
                    }
                });
    }

    @Override
    public void save(Corporation corporation) {
        dataBase.collection("Corps").document(corporation.getCorpId()).set(corporation);
    }

    @Override
    public void delete(Corporation corporation) {
        dataBase.collection("Corps").document(corporation.getCorpId()).delete();
    }
}
