package com.example.capsulate.login;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.Corporation;
import com.example.capsulate.DAO.UserDao;
import com.example.capsulate.MainPageManager;
import com.example.capsulate.MainPageWorker;
import com.example.capsulate.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;

public class LoginClickListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");
    private EditText userNameEditText;
    private EditText passwordEditText;

    public LoginClickListener(EditText userName,EditText password) {
        this.userNameEditText=userName;
        this.passwordEditText=password;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        String userName=userNameEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        checkAuthentication(userName,password,v);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkAuthentication(final String userName, final String password, final View v) {

        UserDao userDao=new UserDao();
        Consumer<Optional<User>> userConsumer = new Consumer<Optional<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void accept(Optional<User> userOpt) {
                if (userOpt.isPresent()) {
                    User user=userOpt.get();
                    if (!user.getPassword().equals(password)){
                        Toast.makeText(v.getContext(), "wrong password", Toast.LENGTH_SHORT).show();
                    }
                    else { // correct!!!

                        Intent intent;
                        switch (user.getRole()){
                            case Worker:
                                intent=new Intent(v.getContext(),MainPageWorker.class);
                                intent.putExtra(User.userIdConst,userName);
                                v.getContext().startActivity(intent);
                                break;
                            case Manager:
                                intent=new Intent(v.getContext(),MainPageManager.class);
                                intent.putExtra(User.userIdConst,userName);
                                v.getContext().startActivity(intent);
                                break;

                        }
                    }

                } else { // no such user
                    Toast.makeText(v.getContext(), "user name doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        };
        userDao.get(userName, userConsumer);
    }
}
