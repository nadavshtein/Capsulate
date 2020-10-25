package com.example.capsulate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.capsulate.login.LoginFirstPage;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BubbleNavigationConstraintView bottomMenu = findViewById(R.id.bottom_navigation);

        setMenuBar(bottomMenu);
    }

    private void setMenuBar(BubbleNavigationConstraintView bottomMenu) {
        bottomMenu.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position){
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new MainPageManagerFragment());
                }
            }
        });
    }


}