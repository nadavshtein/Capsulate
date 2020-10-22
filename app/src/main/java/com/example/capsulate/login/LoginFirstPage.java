package com.example.capsulate.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.capsulate.R;
import com.example.capsulate.SignUp.SignUpMain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ornach.nobobutton.NoboButton;

import java.util.HashMap;
import java.util.Map;

public class LoginFirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_first_page);

        TextView signUpTextView=findViewById(R.id.sign_up);
        setSignUpListener(signUpTextView);

        EditText userName =findViewById(R.id.input_name);
        EditText password =findViewById(R.id.input_password);
        NoboButton loginButton=findViewById(R.id.login);
        loginButton.setOnClickListener(new LoginClickListener(userName,password));
    }


    private void setSignUpListener(TextView signUpTextView) {
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SignUpMain.class);
                startActivity(intent);
            }
        });
    }
}