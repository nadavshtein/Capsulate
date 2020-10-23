package com.example.capsulate.SignUp.Worker;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.util.Consumer;

import com.example.capsulate.Corporation;
import com.example.capsulate.DAO.CorporationDao;
import com.example.capsulate.DAO.UserDao;
import com.example.capsulate.DAO.WorkerDao;
import com.example.capsulate.MainPageWorker;
import com.example.capsulate.SignUp.FieldsChecker;
import com.example.capsulate.UserRole;
import com.example.capsulate.users.User;
import com.example.capsulate.users.Worker;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Optional;

public class SignUpWorkerListener implements View.OnClickListener {

    FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = dataBase.collection("Users");
    CollectionReference corpsCollection = dataBase.collection("Corps");
    EditText fullNameEditText;
    EditText userNameEditText;
    EditText passwordEditText;
    EditText confirmEditText;
    EditText corpIdEditText;

    public SignUpWorkerListener(EditText fullNameEditText, EditText userNameEditText,
                                EditText passwordEditText, EditText confirmEditText,
                                EditText corpIdEditText) {
        this.fullNameEditText = fullNameEditText;
        this.userNameEditText = userNameEditText;
        this.passwordEditText = passwordEditText;
        this.confirmEditText = confirmEditText;
        this.corpIdEditText = corpIdEditText;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        String fullName = fullNameEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmEditText.getText().toString();
        String corpId = corpIdEditText.getText().toString();
        if (FieldsChecker.checkFullName(fullName, v) && FieldsChecker.checkPassword(password, confirm, v)) {
            insertWorker(fullName, userName, password, corpId, v);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertWorker(final String fullName, final String userName, final String password,
                              final String corpId, final View v) {

        final Worker worker = new Worker(fullName, userName, password, UserRole.Worker);
        final UserDao userDao = new UserDao();
        final WorkerDao workerDao = new WorkerDao();
        final CorporationDao corporationDao = new CorporationDao();
        Consumer<Optional<User>> userConsumer = new Consumer<Optional<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void accept(Optional<User> user) {
                if (user.isPresent()) {
                    Toast.makeText(v.getContext(), "user name already exists", Toast.LENGTH_SHORT).show();
                } else {
                    worker.setCorpId(corpId);
                    workerDao.save(worker);
                    findCorporation(corpId);

                }
            }

            private void findCorporation(String corpId) {
                Consumer<Optional<Corporation>> corporationConsumer = new Consumer<Optional<Corporation>>() {

                    @Override
                    public void accept(Optional<Corporation> corporationOpt) {
                        if (corporationOpt.isPresent()) {
                            Corporation corporation = corporationOpt.get();
                            corporation.addEmployee(worker.getUserName());
                            corporationDao.save(corporation);
                            Intent intent = new Intent(v.getContext(), MainPageWorker.class);
                            intent.putExtra(User.userIdConst, worker.getUserName());
                            intent.putExtra(Corporation.corpIdConst, corporation.getCorpId());
                            v.getContext().startActivity(intent);

                        } else {
                            userDao.delete(worker);
                            Toast.makeText(v.getContext(), "incorrect corporation code", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                corporationDao.get(corpId, corporationConsumer);
            }
        };

        userDao.get(worker.getUserName(),userConsumer);

    }
}


