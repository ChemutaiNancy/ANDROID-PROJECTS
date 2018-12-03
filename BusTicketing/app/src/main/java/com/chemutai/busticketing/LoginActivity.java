package com.chemutai.busticketing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtPhone, edtPassword;
    private Button btnLogin, btnRegister;
    private Database db;
    private Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new Database(this);
        mSession = new Session(this);
        edtPhone = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        if (mSession.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                    login();
                break;

            case R.id.btnRegister:

                break;
//                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                default:

        }
    }

    private void login(){
            String phone = edtPhone.getText().toString();
            String password = edtPassword.getText().toString();

            if (db.getUser(phone, password)){
                mSession.setLoggedin(true);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            else
            {
                Toast.makeText(this, "Wrong phone number/password", Toast.LENGTH_SHORT).show();
            }
    }
    // youtube-dl -i PLS1QulWo1RIbb1cYyzZpLFCKvdYV_yJ-E
}
