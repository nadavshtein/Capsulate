package com.example.capsulate.SignUp.Manager;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.capsulate.MainPageManager;
import com.example.capsulate.users.Manager;
import com.example.capsulate.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class SignUpButtonListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");

    private EditText fullNameEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText confirmEditText;

    public SignUpButtonListener(EditText fullNameEditText, EditText userNameEditText,
                                EditText passwordEditText, EditText confirmEditText) {
        this.fullNameEditText = fullNameEditText;
        this.userNameEditText = userNameEditText;
        this.passwordEditText = passwordEditText;
        this.confirmEditText = confirmEditText;
    }

    @Override
    public void onClick(View v) {

        String fullName = fullNameEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmEditText.getText().toString();
        if (checkFullName(fullName, v) && checkPassword(password,confirm)) {
            insertManager(fullName,userName,password,v);
        }
    }


    private void insertManager(final String fullName, final String userName, final String password, final View v) {
        dataBase.collection("Users").whereEqualTo("userName", userName).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document = task.getResult();
                        if (!document.isEmpty()) {
                            Toast.makeText(v.getContext(), "user name already exists", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            insertManager();
                            Intent intent=new Intent(v.getContext(), MainPageManager.class);
                            intent.putExtra("USER_NAME",userName);
                            v.getContext().startActivity(intent);
                        }
                    }

                    private void insertManager() {
                        Manager manager=new Manager(fullName,userName,password);
                        usersCollection.document(userName).set(manager);
                    }
                });
    }

    private boolean checkFullName(String fullName, View v) {
        if (fullName.equals("")) {
            Toast.makeText(v.getContext(), "Please type your name", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private boolean checkPassword(String password, String confirm) {

        return true;
    }
}
