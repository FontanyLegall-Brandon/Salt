package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.Singleton;


public class RegisterActivity extends ActivityForIO {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final EditText etAge = (EditText) findViewById(R.id.etAge);   //on trouve le textview etAge (issu du xml) et on dit qu'il est du type EditText puis on l'appelle etAge
        final EditText etPrenom = (EditText) findViewById(R.id.etPrenom);
        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText etMDP = (EditText) findViewById(R.id.etMDP);
        final Button buttonSinscrire = (Button) findViewById(R.id.buttonSinscrire);

        buttonSinscrire.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etAge.getText().length()==0 ||
                        etEmail.getText().length()==0 ||
                        etMDP.getText().length()==0 ||
                        etNom.getText().length()==0 ||
                        etPrenom.getText().length()==0 ||
                        etPseudo.getText().length()==0) {
                    findViewById(R.id.buttonSinscrire).setEnabled(false);
                    buttonSinscrire.setEnabled(false);
                } else {
                    findViewById(R.id.buttonSinscrire).setEnabled(true);
                    buttonSinscrire.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {//active le bouton inscription seulement quand tous les champs sont remplis

            }
        };

        etAge.addTextChangedListener(textWatcher);
        etPrenom.addTextChangedListener(textWatcher);
        etNom.addTextChangedListener(textWatcher);
        etEmail.addTextChangedListener(textWatcher);
        etPseudo.addTextChangedListener(textWatcher);
        etMDP.addTextChangedListener(textWatcher);

        buttonSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extraction des données saisies
                final String prenom = etPrenom.getText().toString();
                final String nom = etNom.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String email = etEmail.getText().toString();
                final String pseudo = etPseudo.getText().toString();
                final String mdp = etMDP.getText().toString();
                if (Singleton.CLIENT.isConnected()) {//si on est connecté au serveur, on envoie les données
                    RegisterRequest newUser= new RegisterRequest(prenom, nom, age, email, pseudo, mdp);
                    Singleton.CLIENT.sendNewUser(newUser);
                } else {//TODO : prendre en compte les réponses lors de l'inscription
                    Toast.makeText(getApplicationContext(),"Vous n'êtes pas connecté au serveur", Toast.LENGTH_LONG).show();
                }
        }});
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(RegisterActivity.this, Menu.class);
        startActivity(menu);
    }
}