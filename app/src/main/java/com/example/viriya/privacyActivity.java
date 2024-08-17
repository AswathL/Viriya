package com.example.viriya;




import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class privacyActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed

        // Load a website
        webView.loadUrl("https://mbot.blob.core.windows.net/data/privacy.html?sp=r&st=2024-04-04T10:53:47Z&se=2034-04-04T18:53:47Z&spr=https&sv=2022-11-02&sr=b&sig=zatyel1Emgp9nPF8i58qU98WZOTTZBhRcPKubCra%2Bf0%3D");
    }
}
