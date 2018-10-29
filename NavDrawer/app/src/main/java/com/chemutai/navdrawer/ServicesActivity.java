package com.chemutai.navdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        WebView web_services = findViewById(R.id.web_services);

        web_services.loadUrl("file:///android_asset/services.html");
    }
}
