/// i tried show the analytics on terminal but i do not connect to firebase analytics on firebase.

package com.semihsaydam.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Firebase_Analytics extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics; ////Define

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*mFirebaseAnalytics = FirebaseAnalytics.getInstance(this); ///Initialize firebaseAnalytics

        Bundle bundle = new Bundle();
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);*/
    }
}
