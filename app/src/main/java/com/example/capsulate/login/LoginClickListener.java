package com.example.capsulate.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.capsulate.MainActivity;

public class LoginClickListener implements View.OnClickListener {

    private EditText userNameEditText;
    private EditText passwordEditText;

    public LoginClickListener(EditText userName,EditText password) {
        this.userNameEditText=userName;
        this.passwordEditText=password;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(v.getContext(), MainActivity.class);
        String userNameKey="USER_NAME";
        String passwordKey="PASSWORD";
        String userNameValue=userNameEditText.getText().toString();
        String passwordValue=passwordEditText.getText().toString();
        intent.putExtra(userNameKey,userNameValue);
        intent.putExtra(passwordKey,passwordValue);

    }
}
