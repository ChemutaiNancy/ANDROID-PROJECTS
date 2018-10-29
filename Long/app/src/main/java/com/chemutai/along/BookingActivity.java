package com.chemutai.along;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {

    Spinner spinnerRoute, spinnerBusCompany, spinnerSeatNumber;
    TextView txtTravelDate, txtPickPoint;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        spinnerRoute = findViewById(R.id.spinnerRoute);
        spinnerBusCompany = findViewById(R.id.spinnerBusCompany);
        spinnerSeatNumber = findViewById(R.id.spinnerSeatNumber);
        txtTravelDate = findViewById(R.id.txtTravelDate);
        txtPickPoint = findViewById(R.id.txtPickPoint);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
