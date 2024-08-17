package com.example.viriya;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class viriyasongActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton, pauseButton;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toast.makeText(viriyasongActivity.this, "You have found our secret Viriya Song", Toast.LENGTH_SHORT).show();

        // Initialize views
        playButton = findViewById(R.id.start);
        pauseButton = findViewById(R.id.pause);
        seekBar = findViewById(R.id.songProgressBar);

        // Initialize MediaPlayer with your song resource
        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        // Set up click listeners for play and pause buttons
        playButton.setOnClickListener(v -> playSong());
        pauseButton.setOnClickListener(v -> pauseSong());

        // Set up seek bar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed when user starts touching the seek bar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed when user stops touching the seek bar
            }
        });

        // Update seek bar as song plays
        updateSeekBar();
    }

    // Method to start playing the song
    private void playSong() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            updateSeekBar();
        }
    }

    // Method to pause the song
    private void pauseSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    // Method to update seek bar as the song plays
    // Method to update seek bar as the song plays
    private void updateSeekBar() {
        if (mediaPlayer != null) {
            seekBar.setMax(mediaPlayer.getDuration());
            new Thread(() -> {
                while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    try {
                        Thread.sleep(1000); // Update every second
                        runOnUiThread(() -> {
                            if (mediaPlayer != null) {
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Handle back button press
    @Override
    public void onBackPressed() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onBackPressed();
    }


}

