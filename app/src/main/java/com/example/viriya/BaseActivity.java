package com.example.viriya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        // Exit the app when the back button is pressed
        finishAffinity();
    }

    protected void computeWindowSizeClasses() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        float density = getResources().getDisplayMetrics().density;

        float dpWidth = width / density;
        float dpHeight = height / density;

        // Assume thresholds for classifying window sizes
        final float COMPACT_THRESHOLD_DP = 320; // Adjust as needed
        final float MEDIUM_THRESHOLD_DP = 600; // Adjust as needed

        WindowSizeClass windowSizeClass;
        if (dpWidth < COMPACT_THRESHOLD_DP && dpHeight < COMPACT_THRESHOLD_DP) {
            windowSizeClass = WindowSizeClass.COMPACT;
        } else if (dpWidth < MEDIUM_THRESHOLD_DP && dpHeight < MEDIUM_THRESHOLD_DP) {
            windowSizeClass = WindowSizeClass.MEDIUM;
        } else {
            windowSizeClass = WindowSizeClass.EXPANDED;
        }

        // Now you have your window size class
        // You can use it as needed in your application
        // For example, you can log it or perform specific actions based on it
    }

    enum WindowSizeClass {
        COMPACT,
        MEDIUM,
        EXPANDED
    }
}
