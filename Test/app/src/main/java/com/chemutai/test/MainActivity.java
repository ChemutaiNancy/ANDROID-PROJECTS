package com.chemutai.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            Intent intent=new Intent(MainActivity.this, MainPageActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }

    public void open_signUp(View view)
    {
        Intent intent=new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void open_signIn(View view)
    {
        Intent intent=new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
