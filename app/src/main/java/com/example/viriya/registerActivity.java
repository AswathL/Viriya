package com.example.viriya;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    private EditText name, email, pass, confirmpass;

    private Button registerbtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    String UserId;


    private String emailStr, passStr, confimPassStr, nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editTextTextPersonName2);
        email = findViewById(R.id.editTextTextEmail);
        pass = findViewById(R.id.editTextTextPassword);
        confirmpass = findViewById(R.id.editconfirmTextPassword);
        registerbtn = findViewById(R.id.button2);


        fstore=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        /*backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });*/

        registerbtn.setOnClickListener(view -> {
            if (validate()) {

                registerNewUser();
            }

        });

    }

    private boolean validate() {
        nameStr = name.getText().toString().trim();
        passStr = pass.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        confimPassStr = confirmpass.getText().toString().trim();

        if (emailStr.isEmpty()) {
            email.setError("Enter Email");
            return false;
        }
        if (nameStr.isEmpty()) {
            name.setError("Enter  Name");
            return false;
        }
        if (passStr.isEmpty()) {
            pass.setError("Enter password");
            return false;
        }
        if (confimPassStr.isEmpty()) {
            confirmpass.setError("confirm password");
            return false;
        }
        if (passStr.compareTo(confimPassStr) != 0) {
            Toast.makeText(registerActivity.this, " Password and confirm password should be same!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /* private void registerNewUser() {
         mAuth.createUserWithEmailAndPassword(emailStr,passStr)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

                             Toast.makeText(registerActivity.this, " signup Sucessful", Toast.LENGTH_SHORT).show();
                             DbQuery.createuserData(emailStr, nameStr, new mycompletelistner() {
                                 @Override
                                 public void onSuccess() {

                                     DbQuery.loadcategories(new mycompletelistner() {
                                         @Override
                                         public void onSuccess() {
                                             Intent intent = new Intent(registerActivity.this,DashboardActivity.class);
                                             startActivity(intent);
                                             registerActivity.this.finish();

                                         }

                                         @Override
                                         public void onFailure() {

                                             Toast.makeText(registerActivity.this, "Something went wrong Try again!", Toast.LENGTH_SHORT).show();

                                         }
                                     });

                                 }

                                 @Override
                                 public void onFailure() {

                                     Toast.makeText(registerActivity.this, "Something went wrong Try again!", Toast.LENGTH_SHORT).show();
                                 }
                             });


                         } else {

                             Toast.makeText(registerActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
     }*\

     */private void registerNewUser() {
        mAuth.createUserWithEmailAndPassword(emailStr,passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Toast.makeText(registerActivity.this, " signup Sucessful", Toast.LENGTH_SHORT).show();

                            UserId=mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fstore.collection("users").document(UserId);
                            Map<String,Object> user =new HashMap<>();
                            user.put("Name",nameStr);
                            user.put("Email",emailStr);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Log.d("TAG","OnSuccess user profile is created for "+UserId);
                                }

                            });

                            Intent intent = new Intent(registerActivity.this,dashActivity.class);
                            startActivity(intent);
                            registerActivity.this.finish();

                        }
                        else {

                            Toast.makeText(registerActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}