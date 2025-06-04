package com.example.healthquiz;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class OtherFeaturesActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_features);

        webView = findViewById(R.id.webview);

        findViewById(R.id.btn_open_health_site).setOnClickListener(v -> {
            webView.setVisibility(View.VISIBLE);
                WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.loadUrl("https://www.nhc.gov.cn/"); // 国家卫生健康委员会官网
        });
    }
}

