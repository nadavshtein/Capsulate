package com.example.capsulate.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        System.out.println("check");
//        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                       // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                       // Log.w(TAG, "Error adding document", e);
//                    }
//                });

        TextView signUpTextView=findViewById(R.id.sign_up);
        setSignUpListener(signUpTextView);
        NoboButton loginButton=findViewById(R.id.login);
       // loginButton.setOnClickListener();
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