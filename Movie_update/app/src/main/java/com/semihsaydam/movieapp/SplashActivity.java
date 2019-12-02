package com.semihsaydam.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    WifiManager wifiManager; //Define wifiManager
    private ImageView imgGorsel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ///_____________________________Starting Animation___________________________________/////////////

        imgGorsel = (ImageView)findViewById(R.id.imgGorsel);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        imgGorsel.startAnimation(animation);

        ///_____________________________App start with Wifi Control__________________________/////////////

        WifiManager WifiMgr =(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(WifiMgr.isWifiEnabled()){ //// _______________Wifi is Enabled
            new CountDownTimer(5000,1000){
                public void onFinish(){
                    Intent intent =new Intent(SplashActivity.this, LogInActivity.class);  //Splash to Login Screen intent
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                }
                public void onTick(long millisUntilFinished){
                    Toast.makeText(getApplicationContext(),"Loodos",Toast.LENGTH_SHORT).show();
                }
            }.start();

        }else{  //____________________________Wifi is Disabled , Shut Down the App
            Toast.makeText(getApplicationContext(),"U haven't wifi connection",Toast.LENGTH_LONG).show();
            new CountDownTimer(3000,1000){
                public void onFinish(){
                    System.exit(2);
                }
                public void onTick(long millisUntilFinished){
                }
            }.start();

        }

    }

}


