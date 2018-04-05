package com.example.utilisateur.projetl3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.Singleton;

/**
 * Created by inesr on 22/03/2018.
 */

public class WelcomeActivity extends ActivityForIO{
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       // if (Build.VERSION.SDK_INT >= 21) {
       //     getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //}
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this,Menu.class);
                WelcomeActivity.this.startActivity(i);
                WelcomeActivity.this.finish();

            }
        } ,SPLASH_TIME_OUT);




    }



}
