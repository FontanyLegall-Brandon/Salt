package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utilisateur.projetl3.Aides.ListeAides;
import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;
import com.example.utilisateur.projetl3.gameTemplates.TindNumber;

/**
 * Created by Utilisateur on 14/02/2018.
 */
public class MenuPrincipal extends ActivityForIO {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        String login = getIntent().getStringExtra("login");
        if (login == null) {
            login = "guest";
        }
        TextView view = findViewById(R.id.etPseudo);
        view.setText(login);

        Button pommes = findViewById(R.id.pommes);
        pommes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pommesIntent = new Intent(MenuPrincipal.this, PomDragAndDrop.class);
                MenuPrincipal.this.startActivity(pommesIntent);
            }
        });

        Button tindNumber = findViewById(R.id.tindNumber);
        tindNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tindNumberIntent = new Intent(MenuPrincipal.this, TindNumber.class);
                MenuPrincipal.this.startActivity(tindNumberIntent);
            }
        });

        Button profil = findViewById(R.id.buttonProfil);
        profil.setEnabled(false);
        profil.setBackgroundColor(Color.GRAY);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilIntent = new Intent(MenuPrincipal.this, ProfilActivity.class);
                MenuPrincipal.this.startActivity(profilIntent);
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
    }}




