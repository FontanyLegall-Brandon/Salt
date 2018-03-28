package com.example.utilisateur.projetl3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import com.example.utilisateur.projetl3.network.Client;
import com.example.utilisateur.projetl3.network.Singleton;
import com.example.utilisateur.projetl3.utils.LoadingScreen;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Menu extends ActivityForIO {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        final EditText pseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText password = (EditText) findViewById(R.id.etMDP);
        final Button loginButton = (Button) findViewById(R.id.buttonValid);
        final TextView register = (TextView) findViewById(R.id.tvSinscrireici);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Menu.this,RegisterActivity.class);
                Menu.this.startActivity(registerIntent);
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

    /*private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            pass=true;
        }
    }*/
}