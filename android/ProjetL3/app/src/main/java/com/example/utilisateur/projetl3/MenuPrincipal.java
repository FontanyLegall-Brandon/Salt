package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Utilisateur on 14/02/2018.
 */
public class MenuPrincipal extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        String login = getIntent().getStringExtra("login");

        TextView view = findViewById(R.id.textPseudo);
        view.setText(login);
    }
}
