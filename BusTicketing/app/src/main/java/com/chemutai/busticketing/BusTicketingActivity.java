package com.chemutai.busticketing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BusTicketingActivity extends AppCompatActivity {

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_ticketing);

        mImageView = findViewById(R.id.imgBus);
        mTextView = findViewById(R.id.txtBus);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        mTextView.startAnimation(myanim);
        mImageView.startAnimation(myanim);
        final Intent intent = new Intent(this, LoginActivity.class);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);{

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
