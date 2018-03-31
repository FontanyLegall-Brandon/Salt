package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utilisateur.projetl3.Aides.ListeAides;
import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;

/**
 * Created by Utilisateur on 14/02/2018.
 */
public class MenuPrincipal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        String login = getIntent().getStringExtra("login");

        TextView view = findViewById(R.id.etPseudo);
        view.setText(login);

        //Lorsque l'utilisateur clique sur le bouton "jouer" on lance un des jeux adaptés à son niveau.
        Button play = findViewById(R.id.buttonJouer);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jouerIntent = new Intent(MenuPrincipal.this, PomDragAndDrop.class); //On devra faire une classe qui séléctionne et lance differents jeux en fonction du niveau du joueur.
                MenuPrincipal.this.startActivity(jouerIntent);
            }
        });

        Button profil = findViewById(R.id.button_profil);
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




