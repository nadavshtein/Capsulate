package com.example.capsulate.SignUp.Manager;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.Corporation;
import com.example.capsulate.DAO.CorporationDao;
import com.example.capsulate.DAO.ManagerDao;
import com.example.capsulate.DAO.UserDao;
import com.example.capsulate.MainPageManager;
import com.example.capsulate.SignUp.FieldsChecker;
import com.example.capsulate.UserRole;
import com.example.capsulate.users.Manager;
import com.example.capsulate.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Optional;


public class SignUpManagerListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");
    CollectionReference corpsCollection = dataBase.collection("Corps");

    private EditText fullNameEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText confirmEditText;
    private EditText corpIdEditText;
    private EditText corpNameEditText;


    public SignUpManagerListener(EditText fullNameEditText, EditText userNameEditText,
                                 EditText passwordEditText, EditText confirmEditText,
                                 EditText corpIdEditText, EditText corpNameEditText
    ) {
        this.fullNameEditText = fullNameEditText;
        this.userNameEditText = userNameEditText;
        this.passwordEditText = passwordEditText;
        this.confirmEditText = confirmEditText;
        this.corpIdEditText = corpIdEditText;
        this.corpNameEditText = corpNameEditText;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        String fullName = fullNameEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmEditText.getText().toString();
        String corpId = corpIdEditText.getText().toString();
        String corpName = corpNameEditText.getText().toString();

        if (FieldsChecker.checkFullName(fullName, v) && FieldsChecker.checkPassword(password, confirm, v)) {
            insertManager(fullName, userName, password, corpId, corpName, v);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertManager(final String fullName, final String userName, final String password, final String corpId, final String corpName, final View v) {

        final Manager manager = new Manager(fullName, userName, password, UserRole.Manager);
        final UserDao userDao = new UserDao();
        final ManagerDao managerDao = new ManagerDao();
        final CorporationDao corporationDao = new CorporationDao();
        Consumer<Optional<User>> userConsumer = new Consumer<Optional<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void accept(Optional<User> user) {
                if (user.isPresent()) {
                    Toast.makeText(v.getContext(), "user name already exists", Toast.LENGTH_SHORT).show();
                } else {
                    manager.setCorpId(corpId);
                    managerDao.save(manager);
                    Corporation corporation=new Corporation(corpId,corpName);
                    corporation.addEmployee(manager.getUserName());
                    corporationDao.save(corporation);
                    Intent intent=new Intent(v.getContext(),MainPageManager.class);
                    intent.putExtra(User.userIdConst,manager.getUserName());
                    intent.putExtra(Corporation.corpIdConst,manager.getCorpId());
                    v.getContext().startActivity(intent);
                }
            }
        };
        userDao.get(manager.getUserName(), userConsumer);
    }

}
