package com.example.viriya;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class mhcActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Load a website
        webView.loadUrl("https://mbot.blob.core.windows.net/data/index%20(1).html?sp=r&st=2024-03-17T16:38:18Z&se=2030-03-18T00:38:18Z&spr=https&sv=2022-11-02&sr=b&sig=f5Bdq7FvuHQPgLVwIeIFdCNNRu%2BAbucRAA94AwxTLww%3D");
    }
}
