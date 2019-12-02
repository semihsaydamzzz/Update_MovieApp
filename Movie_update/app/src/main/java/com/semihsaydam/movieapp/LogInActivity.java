package com.semihsaydam.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;
    Button button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        /////_____If the user has already logged in do not enter any information____________/////////
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
        if (firebaseUser !=null){
            Intent intent = new Intent(LogInActivity.this,MoviesActivity.class);  //Login to Movies Screen intent
            startActivity(intent); //// If the user restart the program, automatically go to moviesActivity
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

        }
    }

    public void signInClicked(final View view){  /////_______User Sign In with FireBase
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.matches("" )|| password.matches("")){
            Toast.makeText(LogInActivity.this,"Email field cannot be empty.",Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                /////If user succeed go to Movies Screen
                Intent intent = new Intent(LogInActivity.this,MoviesActivity.class);  //Login to Movies Screen intent
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LogInActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void signUpClicked(View view){  /////________User Sign Up with FireBase
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.matches("" )|| password.matches("")){
            Toast.makeText(LogInActivity.this,"Email field cannot be empty.",Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LogInActivity.this,"User Created",Toast.LENGTH_LONG).show();

               /////If user succeed go to Movies Screen
                Intent intent = new Intent(LogInActivity.this,MoviesActivity.class);  //Login to Movies Screen intent
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {///////_____e is firebase warring message. We will use "e" in toast message.
                Toast.makeText(LogInActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }


}

