package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etAge = (EditText) findViewById(R.id.etAge);   //on trouve le textview etAge (issu du xml) et on dit qu'il est du type EditText puis on l'appelle etAge
        final EditText etPrenom = (EditText) findViewById(R.id.etPrenom);
        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etPseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText etMDP = (EditText) findViewById(R.id.etMDP);

        final Button buttonSinscrire = (Button) findViewById(R.id.buttonSinscrire);

        buttonSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sinscrireIntent = new Intent(RegisterActivity.this,Menu.class);
                RegisterActivity.this.startActivity(sinscrireIntent);
//le but etant de continuer et de faire qu'après s'etre inscrit, l'info est envoyée au serveur qui stocke les données et on retourne
                //au menu afin de se connecter avec notre compte
            }
        });
    }
}
