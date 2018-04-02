package com.example.utilisateur.projetl3.gameTemplates;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.R;
import com.example.utilisateur.projetl3.utils.TextDrawable;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * Created by theo on 4/1/18.
 */

public class TindNumber extends ActivityForIO {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_layout);
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        final ArrayList<String> cards = new ArrayList<>();
        cards.add("21 - 9 = 27");
        cards.add("21 - 7 = 14");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.card_item, R.id.helloText, cards);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            private int i = 1;
            int j = 0;

            @Override
            public void onScroll(float f) {

            }

            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                cards.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getApplicationContext(), "Left!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getApplicationContext(), "Right!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                if (i < 20) {
                    cards.add("Carte ".concat(String.valueOf(i)));
                } else if (i == 20) {
                    cards.add("stop");
                    j++;
                } else {
                    cards.add("stop ".concat(String.valueOf(j)).concat(" fois"));
                    j++;
                }
                i++;
                adapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
            }
        });
    }
}
