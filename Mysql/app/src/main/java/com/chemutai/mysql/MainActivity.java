package com.chemutai.mysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    public void OpenReg(View view)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
