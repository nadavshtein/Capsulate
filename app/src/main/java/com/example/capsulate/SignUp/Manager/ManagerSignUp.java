package com.example.capsulate.SignUp.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.capsulate.R;
import com.ornach.nobobutton.NoboButton;

public class ManagerSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_sign_up);
        NoboButton signUp=findViewById(R.id.sign_up_manager);
        EditText fullNameEditText=findViewById(R.id.full_name_manager);
        EditText userNameEditText=findViewById(R.id.user_name_manager);
        EditText passwordEditText=findViewById(R.id.password_manager);
        EditText confirmEditText=findViewById(R.id.confirm_password_manager);
        EditText corpIdEditText=findViewById(R.id.corp_id);
        EditText corpNameEditText=findViewById(R.id.corp_name);

        signUp.setOnClickListener(new SignUpManagerListener(fullNameEditText,userNameEditText,
                passwordEditText,confirmEditText,corpIdEditText,corpNameEditText));
    }




}