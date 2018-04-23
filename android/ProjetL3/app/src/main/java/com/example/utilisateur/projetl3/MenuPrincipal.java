package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utilisateur.projetl3.Aides.ListeAides;
import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberAdd;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberComp;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberMul;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberSub;
import com.example.utilisateur.projetl3.network.Singleton;



/**
 * Created by inesr on 21/04/2018.
 */
public class MenuPrincipal extends ActivityForIO {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        int overallProgress = Singleton.CLIENT.getAvancement();

        TextView avancement = findViewById(R.id.avancement);
        avancement.setText(overallProgress + " / " + Singleton.CLIENT.getMaxProgression());

        String login = getIntent().getStringExtra("successfulLogin");
        if (login == null) {
            login = "guest";
        }
        TextView view = findViewById(R.id.etEmail);
        view.setText(login);

        Button pommes = findViewById(R.id.pommes);
        pommes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pommesIntent = new Intent(MenuPrincipal.this, PomDragAndDrop.class);
                MenuPrincipal.this.startActivity(pommesIntent);
            }
        });

        Button tindNumberAdd = findViewById(R.id.tindNumberAdd);
        tindNumberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tindNumberIntent = new Intent(MenuPrincipal.this, TindNumberAdd.class);
                MenuPrincipal.this.startActivity(tindNumberIntent);
            }
        });

        Button tindNumberSub = findViewById(R.id.tindNumberSub);
        tindNumberSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tindNumberIntent = new Intent(MenuPrincipal.this, TindNumberSub.class);
                MenuPrincipal.this.startActivity(tindNumberIntent);
            }
        });

        Button tindNumberMul = findViewById(R.id.tindNumberMul);
        tindNumberMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tindNumberIntent = new Intent(MenuPrincipal.this, TindNumberMul.class);
                MenuPrincipal.this.startActivity(tindNumberIntent);
            }
        });

        Button tindNumberComp = findViewById(R.id.tindNumberComp);
        tindNumberComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tindNumberComp = new Intent(MenuPrincipal.this, TindNumberComp.class);
                startActivity(tindNumberComp);
            }
        });


        Button aides = findViewById(R.id.buttonAides);
        aides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aidesIntent = new Intent(MenuPrincipal.this, ListeAides.class);
                MenuPrincipal.this.startActivity(aidesIntent);
            }
        });

        enableAt(pommes, 0, overallProgress);
        enableAt(tindNumberAdd, 0, overallProgress);
        enableAt(tindNumberSub, 10, overallProgress);
        enableAt(tindNumberComp, 15, overallProgress);
        enableAt(tindNumberMul, 20, overallProgress);

        ProgressBar progressBar = findViewById(R.id.lvlBar);
        updateProgression(progressBar, overallProgress, Singleton.CLIENT.getMaxProgression());
    }

    public void enableAt(Button button, int needed, int actual) {
        if (actual < needed) {
            //button.setBackgroundColor(Color.GRAY);
            button.setEnabled(false);
        } else {
            //button.setBackgroundColor(Color.WHITE);
            button.setEnabled(true);
        }
    }

    public void updateProgression(ProgressBar bar, int value, int maxValue) {
        bar.setMax(maxValue);
        bar.setProgress(value);
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(MenuPrincipal.this, Menu.class);
        startActivity(menu);
    }
}




