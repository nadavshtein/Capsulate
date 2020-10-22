package com.example.capsulate.SignUp.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.capsulate.R;
import com.example.capsulate.SignUp.Manager.SignUpManagerListener;
import com.ornach.nobobutton.NoboButton;

public class WorkerSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_sign_up);
        NoboButton signUp = findViewById(R.id.sign_up_worker);
        EditText fullNameEditText = findViewById(R.id.full_name_worker);
        EditText userNameEditText = findViewById(R.id.user_name_worker);
        EditText passwordEditText = findViewById(R.id.password_worker);
        EditText confirmEditText = findViewById(R.id.confirm_password_worker);
        EditText corpIdEditText = findViewById(R.id.corp_id_worker);

        signUp.setOnClickListener(new SignUpWorkerListener(fullNameEditText, userNameEditText,
                passwordEditText, confirmEditText, corpIdEditText));
    }
}