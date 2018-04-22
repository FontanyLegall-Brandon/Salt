package com.example.utilisateur.projetl3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utilisateur.projetl3.RegisterActivity;

/**
 * Created by inesr on 22/03/2018.
 */

public class ProfilActivity extends ActivityForIO {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        final TextView tvpseudo = (TextView) findViewById(R.id.tvPseudo);
        EditText etpseudo = (EditText) findViewById(R.id.etEmail);

        final TextView tvemail= (TextView) findViewById(R.id.tvEmail);
        EditText etemail = (EditText) findViewById(R.id.etEmail);
        final TextView tvage= (TextView) findViewById(R.id.tvAge);
        EditText etage= (EditText) findViewById(R.id.etAge);
        final Button logout = (Button) findViewById(R.id.buttonLogout);



}

}
