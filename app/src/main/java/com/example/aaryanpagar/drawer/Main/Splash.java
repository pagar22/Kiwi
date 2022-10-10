package com.example.aaryanpagar.drawer.Main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aaryanpagar.drawer.R;

public class Splash extends AppCompatActivity {

    private int splash_time=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent= new Intent(Splash.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },splash_time);
    }
}
