package com.chemutai.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {
//    public static final String TAG = "FunFactsActivity";

    public static final String TAG = FunFactsActivity.class.getSimpleName();
    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorWheel = new ColorWheel();
    //declare our view variables
    private TextView txtDidYouKnow, txtAnts;
    private Button btnShowFactFun;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        //Assign the views from the layout file to the corresponding variables
//        txtDidYouKnow = findViewById(R.id.txtDidYouKnow);
        txtAnts = findViewById(R.id.txtAnts);
        btnShowFactFun = findViewById(R.id.btnShowFactFun);
        mRelativeLayout = findViewById(R.id.relativeLayout);

       /* btnShowFactFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fact = "Ostriches can run faster than horses";
                txtDidYouKnow.setText(fact);
            }
        });
*/


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fact = mFactBook.getFact();
                int color = mColorWheel.getColor();
                //update the screen with our dynamic fact
                txtAnts.setText(fact);
                btnShowFactFun.setTextColor(color);
//                mRelativeLayout.setBackgroundColor(Color.RED);
                mRelativeLayout.setBackgroundColor(color);
            }
        };

        btnShowFactFun.setOnClickListener(listener);

//        Toast.makeText(this, "Yay an activity was created!", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "we're logging from the onCreate() method!");
    }


}
