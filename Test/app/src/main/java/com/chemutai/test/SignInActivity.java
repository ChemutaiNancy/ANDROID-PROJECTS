package com.chemutai.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        auth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
    }

    public  void signInUser(View view)
    {
        dialog.setMessage("Signing In. Please wait");
        dialog.show();

        if (etEmail.getText().toString().isEmpty() && etPassword.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter Email/Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User Signed in Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
