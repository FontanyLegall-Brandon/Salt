package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;

/**
 * Created by inesr on 22/03/2018.
 */

public class ProfilActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        final TextView tvdate = (TextView) findViewById(R.id.tvDate);
        final EditText etdate = (EditText) findViewById(R.id.etDate);
        final TextView tvpseudo = (TextView) findViewById(R.id.tvPseudo);
        final EditText etpseudo = (EditText) findViewById(R.id.etPseudo);

        final TextView tvemail= (TextView) findViewById(R.id.tvEmail);
        final EditText etemail = (EditText) findViewById(R.id.etEmail);
        final TextView tvage= (TextView) findViewById(R.id.tvAge);
        final EditText etage= (EditText) findViewById(R.id.etAge);
        final Button logout = (Button) findViewById(R.id.buttonLogout);


        logout.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            Intent out = new Intent(ProfilActivity.this,WelcomeActivity.class);
            ProfilActivity.this.startActivity(out);
        }
    });

}}
