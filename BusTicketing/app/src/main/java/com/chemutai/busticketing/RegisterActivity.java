package com.chemutai.busticketing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirstName, etMiddleName, etLastName, etPhoneNumber, etPassword, etConfirmPass;
    private Button btnRegister;
    private TextView txtBack;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);
        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etphoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPass = findViewById(R.id.etConfirmPassword);
        txtBack = findViewById(R.id.txtBack);

        btnRegister.setOnClickListener(this);
        txtBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                break;

            case R.id.txtBack:
                break;

                default:
        }
    }

    private void register(){
        String firstname = etFirstName.getText().toString();
        String middlename = etMiddleName.getText().toString();
        String lastname = etLastName.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        String cpass = etConfirmPass.getText().toString();

        if (phone.isEmpty() && password.isEmpty() && firstname.isEmpty() && middlename.isEmpty() && lastname.isEmpty() && cpass.isEmpty()){
            Toast.makeText(this, "Kindly fill all the fields", Toast.LENGTH_SHORT).show();
        } else
        {
            db.addUser(firstname, middlename, lastname, phone, password, cpass);
            Toast.makeText(this, "Passenger registered", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
