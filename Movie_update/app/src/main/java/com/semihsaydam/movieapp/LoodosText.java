package com.semihsaydam.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class LoodosText extends AppCompatActivity {

    FirebaseRemoteConfig mFirebaseRemoteConfig;
    TextView textView;
    ////////____________Start my App on mobile phone startup_______________________/////
    public class BootUpReceiver extends BroadcastReceiver{
        @NonNull
        @Override
        public void onReceive(Context context, Intent intent){
            if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
                Intent i = new Intent(context, SplashActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loodos_text);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(); //___________Initilaze remote config

        textView =(TextView) findViewById(R.id.message_view);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation); ////___________Set animation for remote config text
        textView.startAnimation(animation);
        long cacheExpiration = 0;
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoodosText.this, "Fetch Succeeded",Toast.LENGTH_SHORT).show();

                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(LoodosText.this, "Fetch Failed",Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();
                    }
                });


        new CountDownTimer(5000,1000){
            public void onFinish(){
                Intent intent =new Intent(LoodosText.this, SplashActivity.class);  //Loodos to Splash Screen intent
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
            public void onTick(long millisUntilFinished){
                Toast.makeText(getApplicationContext(),"Loodos",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void displayWelcomeMessage() {
        textView.setText(mFirebaseRemoteConfig.getString("loodos"));

    }
}
