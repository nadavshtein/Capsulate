package com.example.capsulate.SignUp;

import android.view.View;
import android.widget.Toast;

public class FieldsChecker {

    public static boolean checkFullName(String fullName, View v) {
        if (fullName.equals("")) {
            Toast.makeText(v.getContext(), "Please type your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean checkPassword(String password, String confirm, View v) {

        if (password.length()<4) {
            Toast.makeText(v.getContext(), "password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (!password.equals(confirm)) {
                Toast.makeText(v.getContext(), "passwords doesn't match", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;

    }
}
