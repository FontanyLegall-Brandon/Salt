package com.example.utilisateur.projetl3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.Singleton;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Menu extends ActivityForIO {

    private boolean waitingLoginReply;

    public boolean isWaitingLoginReply() {
        return waitingLoginReply;
    }

    public void setWaitingLoginReply(boolean waitingLoginReply) {
        this.waitingLoginReply = waitingLoginReply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Selection du layout
        setContentView(R.layout.activity_menu);

        // Réccupération de toutes les vues
        final EditText pseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText password = (EditText) findViewById(R.id.etMDP);
        final Button loginButton = (Button) findViewById(R.id.buttonValid);
        final TextView register = (TextView) findViewById(R.id.tvSinscrireici);
        final Button playButton = (Button) findViewById(R.id.playbutton);

        // Par défaut le bouton Jouer est blanc
        playButton.setBackgroundColor(Color.WHITE);

        // Par défaut le bouton de connexion est inactif, il faut taper du texte pour l'activer
        loginButton.setEnabled(false);
        loginButton.setBackgroundColor(Color.GRAY);

        // Permet d'activer le bouton de connexion quand on tape du texte
        TextWatcher activateButtonTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pseudo.getText().toString().length()==0 ||
                        password.getText().toString().length()==0) {
                    findViewById(R.id.buttonValid).setEnabled(false);
                    loginButton.setBackgroundColor(Color.GRAY);
                } else {
                    findViewById(R.id.buttonValid).setEnabled(true);
                    loginButton.setBackgroundColor(Color.WHITE);
                }
            }

            public void afterTextChanged(Editable e) {

            }
        };

        // Application du textWatcher aux deux champs
        pseudo.addTextChangedListener(activateButtonTextWatcher);
        password.addTextChangedListener(activateButtonTextWatcher);

        // Listener permettant de passer à la vue d'inscription en cliquant sur le bouton Inscription
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Menu.this,RegisterActivity.class);
                Menu.this.startActivity(registerIntent);
            }
        });

        // Listener du bouton de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//quand on clique sur le bouton login

                if (isWaitingLoginReply()) { // Si le bouton est en état d'annulation
                    // On remet les boutons dans leur configuration et on annule l'attente
                    stopWaitingForLoginReply(loginButton, playButton);
                    return; // Le reste de la méthode concerne le cas où on attend pas la réponse
                }

                // Désactivation du bouton de passage au menu de jeu
                playButton.setEnabled(false);
                playButton.setBackgroundColor(Color.GRAY);
                // Le bouton de connexion devient un bouton d'annulation
                loginButton.setText("Cancel");

                // On attend la réponse
                setWaitingLoginReply(true);

                if (Singleton.CLIENT.is_connected()) { // Si la connexion avec le serveur est active
                    // On lance l'opération de login sur le serveur
                    Singleton.CLIENT.sendLogin(pseudo.getText().toString(), password.getText().toString());
                    // Un listener du client se chargera de gérer le retour du serveur
                }
                else { // On est pas connecté
                    // On n'attends pas de réponse, donc on remet les boutons dans leur configuration de base
                    stopWaitingForLoginReply(loginButton, playButton);
                    // Et on signifie le problème de connexion à l'utilisateur
                    Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté au serveur", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Listener du bouton Jouer, qui nous envoit simplement dans la vue menue principale
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        // Configuration des polices
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/sfcomicscriptnormal.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Singleton.CLIENT.setActivity(this); //définir l'activité utilisée par le singleton sur l'activité courante
    }

    void stopWaitingForLoginReply(Button loginButton, Button playbutton) {
        loginButton.setText("Se connecter");
        playbutton.setBackgroundColor(Color.WHITE);
        playbutton.setEnabled(true);
        setWaitingLoginReply(false);
    }
}