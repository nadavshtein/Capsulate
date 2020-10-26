package com.example.capsulate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.capsulate.managerFragments.CalendarFragment;
import com.example.capsulate.managerFragments.CapsulesManagementFragment;
import com.example.capsulate.managerFragments.EventsAddFragment;
import com.example.capsulate.managerFragments.FirstPageManagerFragment;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainPageManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_manager);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FirstPageManagerFragment()).commit();
        BubbleNavigationConstraintView bottomMenu = findViewById(R.id.bottom_navigation);

        setMenuBar(bottomMenu);
    }

    private void setMenuBar(BubbleNavigationConstraintView bottomMenu) {
        bottomMenu.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FirstPageManagerFragment()).commit();
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new CapsulesManagementFragment()).commit();
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new EventsAddFragment()).commit();
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new CalendarFragment()).commit();
                }
            }
        });
    }


}