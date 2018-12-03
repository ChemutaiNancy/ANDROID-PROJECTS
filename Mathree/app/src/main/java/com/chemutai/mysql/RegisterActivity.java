package com.chemutai.mathree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText fname, mname, sname, idno, dob, phoneno, password;
    /*String strName, strSurname, strAge, strUsername, strPassword;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.etfName);
        mname = findViewById(R.id.etmName);
        sname = findViewById(R.id.etSurname);
        idno = findViewById(R.id.etidNo);
        dob = findViewById(R.id.etdob);
        phoneno = findViewById(R.id.etPhoneNo);
        password = findViewById(R.id.etPassword);

    }

    public void onReg(View view)
    {
        String strfName = fname.getText().toString();
        String strmName = mname.getText().toString();
        String strsName = sname.getText().toString();
        String stridNo = idno.getText().toString();
        String strDob = dob.getText().toString();
        String strphoneNo = phoneno.getText().toString();
        String strPassword = password.getText().toString();

        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, strfName, strmName, strsName,stridNo, strDob, strphoneNo, strPassword);
    }
}
