package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.Menu;
import com.example.utilisateur.projetl3.MenuPrincipal;
import com.example.utilisateur.projetl3.R;
import com.example.utilisateur.projetl3.RegisterActivity;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.EndCard;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.Operation;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomAddition;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomEquality;
import com.example.utilisateur.projetl3.network.Singleton;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import static com.example.utilisateur.projetl3.network.Singleton.CLIENT;

/**
 * Created by theo on 4/1/18.
 */

public abstract class TindNumber extends ActivityForIO {
    Operation type;

    public TindNumber(Operation type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_layout);

        Button retButton = (Button)findViewById(R.id.buttonRet);
        retButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuPrincipal.class);
                startActivity(intent);
            }
        });
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        final ImageView indicator = findViewById(R.id.indicator);

        final ArrayList<RandomEquality> cards = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            cards.add(type.getRandomEquality());
        }

        final ArrayAdapter<RandomEquality> adapter = new ArrayAdapter<>(this, R.layout.card_item, R.id.helloText, cards);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            private int i = 0;
            private int score = 0;
            private RandomEquality currentCard  = cards.get(0);//on récupère la carte courante
            private boolean finished = false; //on ne compte plus les points si l'exercice est terminé

            @Override
            public void onScroll(float f) {

            }

            @Override
            public void removeFirstObjectInAdapter() {
                cards.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                if (!finished) {
                    if (currentCard.getCorrect()) {
                        indicator.setImageDrawable(getResources().getDrawable(R.drawable.thumbsdown));
                    } else {
                        indicator.setImageDrawable(getResources().getDrawable(R.drawable.thumbsup));
                        score++;
                    }
                }
                if (currentCard != null) {
                    currentCard = cards.get(0);
                }
                i++;
                if (i >= 10) {
                    finished = true;
                    Singleton.CLIENT.incScore(getCodeJeu(), score);
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (!finished) {
                    if (currentCard.getCorrect()) {
                        indicator.setImageDrawable(getResources().getDrawable(R.drawable.thumbsup));
                        score++;
                    } else {
                        indicator.setImageDrawable(getResources().getDrawable(R.drawable.thumbsdown));
                    }
                }
                if (currentCard != null) {
                    currentCard = cards.get(0);
                }
                i++;
                if (i >= 10) {
                    finished = true;
                    CLIENT.incScore(getCodeJeu(), score);//incrémentation de la progression
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                if (i == 9) {
                    cards.add(new EndCard(-1));
                }
                if (i > 9) {
                    cards.add(new EndCard(score));
                    cards.add(new EndCard(score));
                }
            }
        });
    }

    public abstract int getCodeJeu();

    @Override
    public void onBackPressed() {
        Intent menuPr = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(menuPr);
    }
}
