package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by inesr on 22/03/2018.
 */

public class WelcomeActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final Button enter = (Button) findViewById(R.id.buttonEnter);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        enter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent out = new Intent(WelcomeActivity.this,Menu.class); //On devra faire une classe qui séléctionne et lance differents jeux en fonction du niveau du joueur.
                WelcomeActivity.this.startActivity(out);
            }
    });
}}
