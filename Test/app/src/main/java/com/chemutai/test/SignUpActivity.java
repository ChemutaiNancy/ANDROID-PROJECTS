package com.chemutai.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    FirebaseAuth auth;
    ProgressDialog dialog;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        auth = FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
    }

    public void signupUser(View view)
    {
        dialog.setMessage("Registering... Please wait");
        dialog.show();

        String name=etName.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Fill all the values", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User Successfully registered", Toast.LENGTH_SHORT).show();

                                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

                                Users user = new Users(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());

                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                mDatabaseReference.child(firebaseUser.getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(), "User data saved", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(SignUpActivity.this, MainPageActivity.class);
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(), "User data could not be saved", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User could not be registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
          /*  auth.sigWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User Successfully registered", Toast.LENGTH_SHORT).show();
                                *//*Intent intent=new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();*//*
                            }
                            else
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User could not be registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
        }
    }
}
