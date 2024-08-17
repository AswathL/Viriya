package com.example.viriya;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class forumActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Load a website
        webView.loadUrl("https://mbot.blob.core.windows.net/data/Forum.html?sp=r&st=2024-03-17T01:57:20Z&se=2026-03-17T09:57:20Z&spr=https&sv=2022-11-02&sr=b&sig=8QeXTRZUERKVun4l13GdPZm4nYmYck%2BYGnymmEfjSlo%3D");
    }
}