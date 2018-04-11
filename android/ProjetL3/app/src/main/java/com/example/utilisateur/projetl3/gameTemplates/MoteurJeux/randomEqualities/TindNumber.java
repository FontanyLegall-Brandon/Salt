package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.R;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.EndCard;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.Operation;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomAddition;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomEquality;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * Created by theo on 4/1/18.
 */

public class TindNumber extends ActivityForIO {
    Operation type;

    public TindNumber(Operation type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_layout);
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        final ArrayList<RandomEquality> cards = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            cards.add(type.getRandomEquality());
        }

        final ArrayAdapter<RandomEquality> adapter = new ArrayAdapter<>(this, R.layout.card_item, R.id.helloText, cards);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            private int i = -1;
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
                //Do something on the left!
                if (!finished) {
                    if (currentCard.getCorrect()) {
                        Toast.makeText(getApplicationContext(), ":(", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), ":)", Toast.LENGTH_SHORT).show();
                        score++;
                    }
                }
                if (currentCard != null) {
                    currentCard = cards.get(0);
                }
                i++;
                if (i >= 10) {
                    finished = true;
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (!finished) {
                    if (currentCard.getCorrect()) {
                        Toast.makeText(getApplicationContext(), ":)", Toast.LENGTH_SHORT).show();
                        score++;
                    } else {
                        Toast.makeText(getApplicationContext(), ":(", Toast.LENGTH_SHORT).show();
                    }
                }
                if (currentCard != null) {
                    currentCard = cards.get(0);
                }
                i++;
                if (i >= 10) {
                    finished = true;
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
}
