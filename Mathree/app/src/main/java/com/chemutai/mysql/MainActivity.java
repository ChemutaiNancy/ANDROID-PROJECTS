package com.chemutai.mathree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText PhoneNo, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhoneNo = findViewById(R.id.etPhoneNo);
        Password = findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String phone = PhoneNo.getText().toString();
        String password = Password.getText().toString();

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, phone, password);

        startActivity(new Intent(this, HomeActivity.class));
    }

    public void OpenReg(View view)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
