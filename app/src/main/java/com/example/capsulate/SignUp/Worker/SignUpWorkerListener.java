package com.example.capsulate.SignUp.Worker;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.capsulate.Corporation;
import com.example.capsulate.MainPageWorker;
import com.example.capsulate.SignUp.FieldsChecker;
import com.example.capsulate.users.User;
import com.example.capsulate.users.Worker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SignUpWorkerListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");
    CollectionReference corpsCollection = dataBase.collection("Corps");
    EditText fullNameEditText;
    EditText userNameEditText;
    EditText passwordEditText;
    EditText confirmEditText;
    EditText corpIdEditText;

    public SignUpWorkerListener(EditText fullNameEditText, EditText userNameEditText,
                                EditText passwordEditText, EditText confirmEditText,
                                EditText corpIdEditText) {
        this.fullNameEditText = fullNameEditText;
        this.userNameEditText = userNameEditText;
        this.passwordEditText = passwordEditText;
        this.confirmEditText = confirmEditText;
        this.corpIdEditText = corpIdEditText;
    }

    @Override
    public void onClick(View v) {
        String fullName = fullNameEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmEditText.getText().toString();
        String corpId = corpIdEditText.getText().toString();
        if (FieldsChecker.checkFullName(fullName, v) && FieldsChecker.checkPassword(password, confirm, v)) {
            insertWorker(fullName, userName, password, corpId, v);
        }
    }

    private void insertWorker(final String fullName, final String userName, final String password,
                              final String corpId, final View v) {

        dataBase.collection("Users").whereEqualTo("userName", userName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            Toast.makeText(v.getContext(), "user name already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            checkCorpId();
                        }
                    }

                    private void checkCorpId() {
                        dataBase.collection("Corps").whereEqualTo("corpId", corpId).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        QuerySnapshot document = task.getResult();
                                        if (document.isEmpty()) {
                                            Toast.makeText(v.getContext(), "No such corporation Id", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Corporation corporation = document.toObjects(Corporation.class).get(0);
                                            insertToDataBase(corporation);
                                        }
                                    }
                                });
                    }

                    private void insertToDataBase(Corporation corporation) {

                        User worker = new Worker(fullName, userName, password);
                        corporation.addEmployee(userName);
                        worker.setCorpId(corpId);
                        usersCollection.document(userName).set(worker);
                        corpsCollection.document(corpId).set(corporation);
                        Intent intent = new Intent(v.getContext(), MainPageWorker.class);
                        intent.putExtra(User.userIdConst,worker.getUserName());
                        intent.putExtra(Corporation.corpIdConst,worker.getCorpId());
                        v.getContext().startActivity(intent);
                    }
                });
    }
}

