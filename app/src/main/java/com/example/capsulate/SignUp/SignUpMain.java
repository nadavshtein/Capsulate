package com.example.capsulate.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.capsulate.R;
import com.example.capsulate.login.LoginClickListener;
import com.example.capsulate.login.LoginFirstPage;
import com.ornach.nobobutton.NoboButton;

public class SignUpMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_main);
        NoboButton managerButton=findViewById(R.id.managerButton);
        NoboButton workerButton=findViewById(R.id.workerButton);
        setManagerButtonListener(managerButton);
        setWorkerButtonListener(workerButton);
    }

    private void setWorkerButtonListener(NoboButton workerButton) {
        workerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),WorkerSignUp.class));
            }
        });
    }

    private void setManagerButtonListener(NoboButton managerButton) {
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),ManagerSignUp.class));
            }
        });
    }


}
