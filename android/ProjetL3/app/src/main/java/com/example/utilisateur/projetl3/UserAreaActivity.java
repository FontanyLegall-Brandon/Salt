package com.example.utilisateur.projetl3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText pseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText age = (EditText) findViewById(R.id.etAge);
        final TextView welcomemsg = (TextView) findViewById(R.id.tvWelcomeMsg);


    }
  }
