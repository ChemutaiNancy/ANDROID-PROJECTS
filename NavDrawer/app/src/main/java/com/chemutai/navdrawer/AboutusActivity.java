package com.chemutai.navdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        WebView web_about_us = findViewById(R.id.web_about_us);

        web_about_us.loadUrl("file:///android_asset/about_us.html");
    }
}
