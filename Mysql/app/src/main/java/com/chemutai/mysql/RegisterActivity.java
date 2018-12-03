package com.chemutai.mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText name, surname, age, username, password;
    /*String strName, strSurname, strAge, strUsername, strPassword;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etName);
        surname = findViewById(R.id.etSurname);
        age = findViewById(R.id.etAge);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);

    }

    public void onReg(View view)
    {
        String strName = name.getText().toString();
        String strSurname = surname.getText().toString();
        String strAge = age.getText().toString();
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();

        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, strName, strSurname, strAge, strUsername, strPassword);
    }
}
