package com.example.utilisateur.projetl3;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
                if (Singleton.CLIENT.is_connected()) {//si on est connecté au serveur, on envoie les données
                    RegisterRequest newUser= new RegisterRequest(prenom, nom, age, email, pseudo, mdp);
                    Singleton.CLIENT.sendNewUser(newUser);
                } //TODO : finir et gérer les problèmes d'inscription
        }});
    }
}