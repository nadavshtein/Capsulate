package com.example.capsulate.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.capsulate.R;
import com.ornach.nobobutton.NoboButton;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        NoboButton managerButton=findViewById(R.id.managerButton);
        NoboButton workerButton=findViewById(R.id.workerButton);
        setManagerButtonListener(managerButton);
        setWorkerButtonListener(workerButton);


    }

    private void setWorkerButtonListener(NoboButton workerButton) {

        workerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext() ,WorkerLogin.class));
            }
        });
    }

    private void setManagerButtonListener(NoboButton managerButton) {

        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext() ,ManagerLogin.class));
            }
        });
    }
}