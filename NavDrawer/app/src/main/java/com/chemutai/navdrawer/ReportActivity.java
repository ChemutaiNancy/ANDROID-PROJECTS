package com.chemutai.navdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        WebView web_report = findViewById(R.id.web_report);

        web_report.loadUrl("file:///android_asset/report.html");
    }
}
