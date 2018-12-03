package com.chemutai.busticketing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private Session mSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSession = new Session(this);
        if (!mSession.loggedin()){
            logout();
        }
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        mSession.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
