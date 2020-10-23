package com.example.capsulate.DAO;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;

public class UserDao implements Dao<User> {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void get(String userName, final Consumer<Optional<User>> consumer) {

        dataBase.collection("Users").whereEqualTo("userName", userName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            consumer.accept(Optional.of(document.toObjects(User.class).get(0)));
                        } else {
                            consumer.accept(Optional.<User>empty());
                        }
                    }
                });


    }

    @Override
    public void save(User user) {
        dataBase.collection("Users").document(user.getUserName()).set(user);
    }


    @Override
    public void delete(final User user) {
        dataBase.collection("Users").document(user.getUserName()).delete();
    }
}
