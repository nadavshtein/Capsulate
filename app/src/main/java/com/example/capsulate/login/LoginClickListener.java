package com.example.capsulate.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.capsulate.Corporation;
import com.example.capsulate.MainActivity;
import com.example.capsulate.MainPageManager;
import com.example.capsulate.MainPageWorker;
import com.example.capsulate.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginClickListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");
    private EditText userNameEditText;
    private EditText passwordEditText;

    public LoginClickListener(EditText userName,EditText password) {
        this.userNameEditText=userName;
        this.passwordEditText=password;
    }

    @Override
    public void onClick(View v) {

        String userName=userNameEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        checkAuthentication(userName,password,v);

    }

    private void checkAuthentication(String userName, String password, final View v) {

        dataBase.collection("Users").whereEqualTo("userName", userName)
                .whereEqualTo("password",password).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot document=task.getResult();
                        if(document.isEmpty()){
                            Toast.makeText(v.getContext(), "Please try again", Toast.LENGTH_SHORT).show();
                        }else {

                            Intent intent;
                            User user=document.toObjects(User.class).get(0); // problem!!!!!!!!!!!!!!!!!!!!
                            if(user.isManager()){
                                intent=new Intent(v.getContext(), MainPageManager.class);
                            }
                            else {
                                intent=new Intent(v.getContext(), MainPageWorker.class);
                            }
                            intent.putExtra(User.userIdConst,user.getUserName());
                            intent.putExtra(Corporation.corpIdConst,user.getCorpId());
                            v.getContext().startActivity(intent);
                        }
                    }

                });
    }
}
