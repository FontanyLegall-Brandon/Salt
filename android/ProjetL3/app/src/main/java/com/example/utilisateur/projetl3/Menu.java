package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import com.example.utilisateur.projetl3.network.Client;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        boolean connected = false;

        final EditText pseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText password = (EditText) findViewById(R.id.etMDP);
        final Button loginButton = (Button) findViewById(R.id.buttonValid);
        final TextView register = (TextView) findViewById(R.id.tvSinscrireici);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent registerIntent = new Intent(Menu.this,RegisterActivity.class);
                //Menu.this.startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuPrincipal.class);
                EditText pseudo = findViewById(R.id.etPseudo);//pseudo récupéré du champs de texte
                intent.putExtra("login", pseudo.getText().toString());
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), "Non connecté", Toast.LENGTH_LONG).show();

    }
}