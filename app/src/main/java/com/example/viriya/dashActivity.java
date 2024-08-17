package com.example.viriya;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class dashActivity extends BaseActivity {

    private ImageView chatimg, map, videocall, voicecall, helpline,profile,report,mhc,profile1,calendar,forum,qabot;
    GoogleSignInOptions gso;
    private FirebaseFirestore fstore;
    private Button sta;
    private FirebaseAuth mAuth;
    String UserId;
    private TextView Name,quot1;

    private String[] quotesdisplay = {
            "BE THE CHANGE",
            "NEVER GIVE UP",
            "SUCCESS IS YOURS",
            "ALL IS WELL",
            "IT IS POSSIBLE",
            "BELIEVE YOU CAN",
            "DO YOUR BEST",
            "FOCUS AND WIN",
            "SEE THE GOOD",

            // Add more quotes as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatimg = findViewById(R.id.chatbot);
        Name = findViewById(R.id.textView5);
        quot1 = findViewById(R.id.quot1);
        map = findViewById(R.id.mapss);
        voicecall = findViewById(R.id.voicecall);
        helpline = findViewById(R.id.helpline1);
        profile = findViewById(R.id.imageView10);
        profile1 = findViewById(R.id.profile);
        report = findViewById(R.id.report);
        calendar = findViewById(R.id.calandar);
        forum = findViewById(R.id.forum);
        qabot = findViewById(R.id.qabot);
        sta = findViewById(R.id.sta);
        mhc = findViewById(R.id.mhc);

        fstore= FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();

        UserId=mAuth.getCurrentUser().getUid();

        DocumentReference documentReference=fstore.collection("users").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    // Handle any errors here
                    return;
                }

                if (value != null && value.exists()) {
                    // Data from Firestore exists for the user// Assuming "email" is the field name in Firestore
                    String userName = value.getString("Name"); // Assuming "name" is the field name in Firestore

                    // Set the email and name fields in your UI

                    Name.setText(userName);
                }
            }
        });

        gso = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personename=acct.getDisplayName();
            String email=acct.getEmail();
            Name.setText(personename);

            Uri personPhoto = acct.getPhotoUrl();
            if (personPhoto != null) {
                String photoUrl = personPhoto.toString();
                Picasso.get().load(photoUrl).into(profile);
            }
        }

        chatimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, chatActivity.class);
                startActivity(intent);
            }
        });
        quot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, viriyasongActivity.class);
                startActivity(intent);
            }
        });
        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, chatActivity.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(dashActivity.this, "Opening Maps...", Toast.LENGTH_SHORT).show();
                // Define the search query
                String query = "Psychiatrist near me";

                // Create a Uri with the query to open in Google Maps
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + query);

                // Create an Intent to open Google Maps with the search query
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set the package for the Intent to ensure it opens in Google Maps
                mapIntent.setPackage("com.google.android.apps.maps");

                // Check if Google Maps is installed on the device
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    // Open Google Maps
                    startActivity(mapIntent);
                } else {
                    // Handle the case where Google Maps is not installed
                    // You can prompt the user to install Google Maps or use a different map app.
                    Toast.makeText(dashActivity.this, "Google Maps is not installed on this device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        voicecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+1 205-203-8355"));
                startActivity(intent);
            }
        });

        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Helpline Click", "Helpline ImageView clicked");
                Toast.makeText(dashActivity.this, "Helpline Clicked", Toast.LENGTH_SHORT).show();
                // Add your code here to handle the click event
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800-891-4416"));
                startActivity(intent);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace 'https://example.com' with the URL you want to redirect to
                Intent intent =new Intent(dashActivity.this, reportActivity.class);
                startActivity(intent);

            }

        });

        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, profileActivity.class);
                startActivity(intent);
            }
        });
        mhc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, mhcActivity.class);
                startActivity(intent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, aboutActivity.class);
                startActivity(intent);
            }
        });
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashActivity.this, forumActivity.class);
                startActivity(intent);
            }
        });
        qabot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dashActivity.this, "Type Hi to start asking questions", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(dashActivity.this, qaActivity.class);
                startActivity(intent);
            }
        });
        computeWindowSizeClasses();
    }

    @Override
    public void onBackPressed() {
        // Navigate back to LoginActivity
        Intent intent = new Intent(dashActivity.this, introActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity
    }

    private void openGoogleMeet() {
        // Create an intent with a specific URI to open the Google Meet link
        // Create an intent with a specific URI to open the Google Calendar app
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.google.android.apps.meetings");

        // Try to open the Google Calendar app
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If the Google Calendar app is not available, you can take another action,
            // such as opening a web page with Google Calendar.
            // For example:
            Uri meetWebUri = Uri.parse("https://meet.google.com/dfu-dmrk-ird");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, meetWebUri);
            startActivity(webIntent);
        }
    }

    private void openGoogleCalendar() {
        // Create an intent with a specific URI to open the Google Calendar app
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.google.android.calendar");

        // Try to open the Google Calendar app
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If the Google Calendar app is not available, you can take another action,
            // such as opening a web page with Google Calendar.
            // For example:
            Uri calendarWebUri = Uri.parse("https://calendar.google.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, calendarWebUri);
            startActivity(webIntent);
        }
    }

    private String getRandomQuote() {
        // Generate a random index within the bounds of the quotes array
        int randomIndex = new Random().nextInt(quotesdisplay.length);
        // Return the quote at the random index
        return quotesdisplay[randomIndex];
    }
}
