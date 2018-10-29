package com.chemutai.navdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        WebView web = findViewById(R.id.web);

        web.loadUrl("file:///android_asset/products.html");
    }
}
