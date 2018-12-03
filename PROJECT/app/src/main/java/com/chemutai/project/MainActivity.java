package com.chemutai.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity {
    MaterialEditText name, email, phoneNo, tDate, tfrom, tto;
    Button btnBook;
    SpotsDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);
        phoneNo=findViewById(R.id.etPhoneNo);
        tDate=findViewById(R.id.etDate);
        tfrom=findViewById(R.id.etFrom);
        tto=findViewById(R.id.etTo);

        progress=new SpotsDialog(this);
    }

    public void save(View view) {
        final String names=name.getText().toString();
        String mail=email.getText().toString();
        final String phone=phoneNo.getText().toString();
        final String travelDate=tDate.getText().toString();
        String from=tfrom.getText().toString();
        String to=tto.getText().toString();

        if (names.isEmpty() && mail.isEmpty()&& phone.isEmpty() && travelDate.isEmpty() && from.isEmpty() && to.isEmpty()){
            Toast.makeText(this, "Fill all the data", Toast.LENGTH_SHORT).show();
        }

            int phoneNum=Integer.parseInt(phone);

        progress.show();

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();//package data in to a form
        params.put("Names", names);
        params.put("Email", mail);
        params.put("Phone Number", phoneNum);
        params.put("Travel Date", travelDate);
        params.put("From", from);
        params.put("To", to);

        String url="http://jistymarketer.com/Booking/save.php";

        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to save, try again", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Result "+responseString, Toast.LENGTH_SHORT).show();

                if (responseString.contains("failed")){
                    Toast.makeText(MainActivity.this, "Failed to save, try again", Toast.LENGTH_SHORT).show();
                }
                else{
                    name.setText("");
                    email.setText("");
                    phoneNo.setText("");
                    tDate.setText("");
                    tfrom.setText("");
                    tto.setText("");

                    Toast.makeText(MainActivity.this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

public void fetch(){
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
}
}
