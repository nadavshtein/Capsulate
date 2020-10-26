package com.example.capsulate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.capsulate.DAO.ManagerDao;
import com.example.capsulate.managerFragments.CalendarFragment;
import com.example.capsulate.managerFragments.CapsulesManagementFragment;
import com.example.capsulate.managerFragments.EventsAddFragment;
import com.example.capsulate.managerFragments.FirstPageManagerFragment;
import com.example.capsulate.users.Manager;
import com.example.capsulate.users.User;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.Optional;

public class MainPageManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_manager);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstPageManagerFragment()).commit();
        BubbleNavigationConstraintView bottomMenu = findViewById(R.id.bottom_navigation);
        Intent intent=getIntent();
        String managerUserName=intent.getStringExtra(User.userIdConst);
        getManager(managerUserName,bottomMenu);
    }

    private void getManager(String managerUserName, final BubbleNavigationConstraintView bottomMenu) {

        ManagerDao managerDao=new ManagerDao();
        Consumer<Optional<Manager>> managerConsumer = new Consumer<Optional<Manager>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void accept(Optional<Manager> managerOpt) {
                if(managerOpt.isPresent()) {
                    setMenuBar(managerOpt.get(), bottomMenu);
                }
            }
        };
        managerDao.get(managerUserName,managerConsumer);

    }


    private void setMenuBar(final Manager manager, BubbleNavigationConstraintView bottomMenu) {
        bottomMenu.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                Fragment fragment;
                switch (position) {
                    case 0:
                        fragment = new FirstPageManagerFragment();
                        break;
                    case 1:
                        fragment = new CapsulesManagementFragment();
                        break;
                    case 2:
                        fragment = new EventsAddFragment();
                        break;
                    case 3:
                        fragment = new CalendarFragment();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
//                Bundle bundle=new Bundle();
//                bundle.putParcelable("Manager", (Parcelable) manager);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
            }
        });
    }


}