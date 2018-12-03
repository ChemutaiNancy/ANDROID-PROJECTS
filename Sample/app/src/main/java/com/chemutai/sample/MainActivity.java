package com.chemutai.sample;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFname, etMname, etSname, etDateofb, etPhonenumb, etPass;
    private Button btnReg;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFname = findViewById(R.id.etFirstname);
        etMname = findViewById(R.id.etMiddlename);
        etSname = findViewById(R.id.etSurname);
        etDateofb = findViewById(R.id.etDob);
        etPhonenumb = findViewById(R.id.etPhoneno);
        etPass = findViewById(R.id.etPassword);

        btnReg = findViewById(R.id.btnRegister);

        progressDialog = new ProgressDialog(this);

        btnReg.setOnClickListener(this);
    }

    private void registerPassenger(){
        final String firstName = etFname.getText().toString().trim();
        final String middleName = etMname.getText().toString().trim();
        final String surname = etSname.getText().toString().trim();
        final String dob = etDateofb.getText().toString().trim();
        final String phoneNo = etPhonenumb.getText().toString().trim();
        final String password = etPass.getText().toString().trim();

        progressDialog.setMessage("Registering Passenger...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstName);
                params.put("middlename", middleName);
                params.put("surname", surname);
                params.put("dob", dob);
                params.put("phoneno", phoneNo);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == btnReg)
        registerPassenger();
    }
}
