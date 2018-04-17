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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final EditText pseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText password = (EditText) findViewById(R.id.etMDP);
        final Button loginButton = (Button) findViewById(R.id.buttonValid);
        final TextView register = (TextView) findViewById(R.id.tvSinscrireici);
        final Button playbutton = (Button) findViewById(R.id.playbutton);
        findViewById(R.id.buttonValid).setEnabled(false);
        loginButton.setBackgroundColor(Color.GRAY);

        TextWatcher textWatcher = new TextWatcher() {
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

        pseudo.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Menu.this,RegisterActivity.class);
                Menu.this.startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//quand on clique sur le bouton login
                if (Singleton.CLIENT.is_connected()) {
                    Intent intent = new Intent(Menu.this, MenuPrincipal.class);
                    EditText pseudo = findViewById(R.id.etPseudo);//pseudo récupéré du champs de texte
                    intent.putExtra("login", pseudo.getText().toString());
                    Singleton.CLIENT.sendLogin(pseudo.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté au serveur", Toast.LENGTH_LONG).show();
                }
            }
        });

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/sfcomicscriptnormal.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    Singleton client = Singleton.CLIENT;
    client.setActivity(this);//définir l'activité utilisée par le singleton sur l'activité courante
    }
    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Connexion en cours...",
                    Toast.LENGTH_LONG).show();
        }
    }
}