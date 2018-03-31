package com.example.utilisateur.projetl3.Aides;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.utilisateur.projetl3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utilisateur on 31/03/2018.
 *
 * Progression possible : Récupérer les aides depuis la base de données au lieu d'en avoir un nombre limité.
 */

public class ListeAides extends Activity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aides);

        mListView = (ListView) findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aide item = (Aide)adapterView.getItemAtPosition(i);

                Intent intent = new Intent(ListeAides.this, AideActivity.class);
                intent.putExtra("Titre", item.getIntitule());

                startActivity(intent);
            }
        });

        List<Aide> aides = genererAides();

        AidesAdapter adapter = new AidesAdapter(ListeAides.this, aides);
        mListView.setAdapter(adapter);
    }

    private List<Aide> genererAides(){
        List<Aide> aides = new ArrayList<Aide>();
        aides.add(new Aide(Color.WHITE, "Les nombres", "Donner un nombre à ses pommes."));
        aides.add(new Aide(Color.GREEN, "Compter", "Compter ses pommes."));
        aides.add(new Aide(Color.CYAN, "(+) Les additions", "Acheter des pommes."));
        aides.add(new Aide(Color.BLUE, "(-) La soustraction", "Manger ses pommes."));
        aides.add(new Aide(Color.RED, "(*) La multiplication", "Prendre des sacs de pommes."));
        aides.add(new Aide(Color.GRAY, "(/) La division", "Partager les pommes."));
        return aides;
    }


}
