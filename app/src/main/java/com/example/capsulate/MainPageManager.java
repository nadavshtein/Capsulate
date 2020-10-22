package com.example.capsulate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.capsulate.users.User;

public class MainPageManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_manager);
        Intent intent=getIntent();
        String userName=intent.getStringExtra("USER_NAME");

    }
}