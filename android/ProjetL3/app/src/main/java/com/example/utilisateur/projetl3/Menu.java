package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        boolean connected = false;
        final Button loginButton = (Button) findViewById(R.id.buttonValid);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuPrincipal.class);
                EditText pseudo = findViewById(R.id.textPseudo);//pseudo récupéré du champs de texte
                intent.putExtra("login", pseudo.getText().toString());
                startActivity(intent);
            }
        });
        while (!connected) {
            try {
                Socket mSocket = IO.socket("http://46.193.0.89:1345");
                mSocket.connect();
                Toast.makeText(getApplicationContext(), "Connexion réussie!", Toast.LENGTH_LONG).show();
                connected = true;
            } catch (URISyntaxException e) {
                Toast.makeText(getApplicationContext(), "Errreur de connexion", Toast.LENGTH_LONG).show();
            }
        }
    }
}