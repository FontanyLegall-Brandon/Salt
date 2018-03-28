package com.example.utilisateur.projetl3.gameTemplates;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utilisateur.projetl3.R;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.DragAndDrop;
import com.example.utilisateur.projetl3.utils.GameUI;
import com.example.utilisateur.projetl3.utils.UIInteractionsListener;

/**
 * Created by Utilisateur on 01/03/2018.
 */

public class PomDragAndDrop extends AppCompatActivity {

    private DragAndDrop moteur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pom_drag_and_drop);
        ViewGroup panier = findViewById(R.id.panier);
        panier.setOnDragListener(new MyDragListener());
        ViewGroup zoneJeu =findViewById(R.id.zoneJeu);
        zoneJeu.setOnDragListener(new MyDragListener());
        //Par la suite on pourra faire en sorte que ce soit DragAndDrop qui prenne un nombre au hasard en fonction du niveau du joueur.
        // Via ce constructeur : moteur = new DragAndDrop(3);
        moteur = new DragAndDrop();
        //On ajoute dynamiquement le nombre de poms' présentes afin de pouvoir remplir le but.
        for(int i=0; i<moteur.getStock(); i++){
            View pom = LayoutInflater.from(this).inflate(R.layout.component_pom, zoneJeu, false);
            pom.setOnTouchListener(new MyTouchListener());
            zoneJeu.addView(pom);
        }
        TextView intitule =findViewById(R.id.intitule);
        intitule.setText("On veut "+moteur.getGoal()+" pom' dans le panier.");

        //Le listener pour le menu de bas d'activité.
        GameUI gameUi =findViewById(R.id.gameUi);
        gameUi.setUiListener(new UIInteractionsListener() {
            @Override
            public void onUIInteraction(String type) {
                Log.d("PomDragAndDrop","On a cliqué sur le gameUI et l'event est : "+type+".");
                if(type.equals("valid")){
                    //On vérifie que la réponse est juste.
                    ViewGroup panier = findViewById(R.id.panier);
                    int res =moteur.verifWin(panier.getChildCount()-1);
                    if(res==1){
                        Toast.makeText(getApplicationContext(), "Victoire!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if(res==-1){
                            Toast.makeText(getApplicationContext(), "Perdu, il n'y avait pas assez de poms.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Perdu, il y avait trop de poms.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    //Remettre le jeu à 0
                }
            }
        });
    }

    public void majCmpt(ViewGroup container){
        int count = container.getChildCount();
        //Cherche un endroit pour afficher le nombre de poms' contenues.
        TextView cmpt = container.findViewWithTag("cmpt");
        if(cmpt !=null) {
            //On prend "count-1" car le TextView est considéré comme un enfant.
            cmpt.setText("Contient " + (count - 1) + " poms'.");
        }
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View pom, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        pom);
                pom.startDrag(data, shadowBuilder, pom, 0);
                pom.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                /*case DragEvent.ACTION_DRAG_STARTED:
                    //Se déclenche quand on drag cet objet
                    break;*/
                /*case DragEvent.ACTION_DRAG_ENTERED:
                    //Se déclenche quand un objet est drag au dessus de celui-ci
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Se déclenche quand un objet n'est plus drag au dessus de celui-ci
                    break;*/
                case DragEvent.ACTION_DROP:
                    //Se déclenche quand l'objet est laché.
                    View pom = (View) event.getLocalState();
                    ViewGroup emplacementActu = (ViewGroup) pom.getParent();

                    if(v.getTag().equals("container")) {
                        ViewGroup container = (ViewGroup) v;
                        emplacementActu.removeView(pom);
                        container.addView(pom);
                        //On met à jour l'ancien emplacement de l'objet droppé ainsi que le nouveau.
                        majCmpt(emplacementActu);
                        majCmpt(container);
                    }
                    pom.setVisibility(View.VISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //Se déclenche quand on termine le drop (n'est pas semblable au précédent, il se déclenche une fois toutes les opérations du drop terminées.)
                    View pomf = (View) event.getLocalState();
                    pomf.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
