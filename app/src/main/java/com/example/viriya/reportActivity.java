package com.example.viriya;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class reportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button viewScreenshotButton = findViewById(R.id.button_view_screenshot);
        viewScreenshotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolderUsingSAF();
            }
        });
    }

    private void openFolderUsingSAF() {
        // Provide the URI of the desired folder
        Uri folderUri = Uri.parse("file:///storage/emulated/0/Pictures/mentReport");

        // Create an intent to open the folder using SAF
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, folderUri);
        startActivityForResult(intent, 1); // Request code for handling the result
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Handle the selected folder here
            Uri folderUri = data.getData();
            // Use the Uri object to access the folder contents with limited read-only capabilities
        }
    }
    @Override
    public void onBackPressed() {
        // Create an intent to start the DashActivity
        Intent intent = new Intent(this, dashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
        startActivity(intent);
        finish(); // Finish the current activity
    }

}

