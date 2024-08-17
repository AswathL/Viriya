package com.example.viriya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Get references to buttons
        Button privacyPolicyButton = findViewById(R.id.privacyPolicyButton);
        Button termsButton = findViewById(R.id.termsButton);

        // Set click listeners for buttons
        privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for Privacy Policy button
                Toast.makeText(aboutActivity.this, "Privacy Policy clicked", Toast.LENGTH_SHORT).show();
                // Replace the Toast with your desired functionality
                Intent intent = new Intent(aboutActivity.this, privacyActivity.class);
                startActivity(intent);


            }
        });

        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for Terms and Conditions button
                Toast.makeText(aboutActivity.this, "Terms and Conditions clicked", Toast.LENGTH_SHORT).show();
                // Replace the Toast with your desired functionality
                Intent intent = new Intent(aboutActivity.this, termsActivity.class);
                startActivity(intent);
            }
        });
    }
}
