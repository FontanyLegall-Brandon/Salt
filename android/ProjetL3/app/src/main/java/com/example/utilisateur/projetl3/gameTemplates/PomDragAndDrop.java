package com.example.utilisateur.projetl3.gameTemplates;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.MenuPrincipal;
import com.example.utilisateur.projetl3.R;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.DragAndDrop;
import com.example.utilisateur.projetl3.utils.GameUI;
import com.example.utilisateur.projetl3.utils.UIInteractionsListener;

/**
 * Created by Utilisateur on 01/03/2018.
 */

public class PomDragAndDrop extends ActivityForIO {

    private DragAndDrop moteur;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pom_drag_and_drop);

        final ViewGroup panier = findViewById(R.id.panier);
        final ViewGroup zoneJeu = findViewById(R.id.zoneJeu);
        final TextView intitule = findViewById(R.id.intitule);
        final GameUI gameUi = findViewById(R.id.gameUi);

        moteur = new DragAndDrop();
        /*Par la suite on pourra faire en sorte que ce soit DragAndDrop qui prenne un nombre au hasard en fonction du niveau du joueur.
        Via ce constructeur : moteur = new DragAndDrop(nb);*/

        panier.setOnDragListener(new PomDragListener());
        zoneJeu.setOnDragListener(new PomDragListener());

        //On ajoute dynamiquement le nombre de poms' présentes afin de pouvoir remplir le but.
        ajouterPomsDans(moteur.getStock(), zoneJeu);
        intitule.setText("On veut " + moteur.getGoal() + " poms' dans le panier.");

        //Le listener pour le menu de bas d'activité.
        gameUi.setUiListener(new UIInteractionsListener() {
            @Override
            public void onUIInteraction(String type) {
                if(type.equals("valider")){
                    //On vérifie que la réponse est juste.

                    int res = moteur.verifWin(panier.getChildCount());

                    if (res==1) {   // Si on a le nombre de pommes demandé dans le panier
                        Toast.makeText(getApplicationContext(), "Victoire!", Toast.LENGTH_SHORT).show();
                        /* Aucune idée de ce à quoi ça correspond */
                        SharedPreferences prefs = getSharedPreferences("exercicePom", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("prog", res); //progression correspondant au res
                        editor.commit();
                        /* Sauvegarde du score ? */
                        Log.d("sp res=1", "score sauvegarde");

                        //TODO : sauvegarder la progression.
                        finish();   // Met un terme à l'activité
                    }
                    else {

                        if(res==-1){    // On a pas assez de pommes
                            Toast.makeText(getApplicationContext(), "Perdu, il n'y a pas assez de poms.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {  // On a trop de pommes
                            Toast.makeText(getApplicationContext(), "Perdu, il y a trop de poms.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        SharedPreferences prefs = getSharedPreferences("exercicePom", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("prog", res); //progression correspondant au res
                        editor.commit();
                        //Ici on pourra sauvegarder le nombre de fois qu'un joueur à échoué afin de, soit le sauvegarder définitivement, soit diminuer la récompense.
                    }
                }
                else{
                    if(type.equals("recommencer")){
                        Toast.makeText(getApplicationContext(), "Recommencer.",Toast.LENGTH_SHORT).show();
                        reInit();
                        SharedPreferences prefs = getSharedPreferences("exercicePom", Context.MODE_PRIVATE);
                        int score = prefs.getInt("prog", 0); //0 is the default value

                    }
                }
            }
        });
    }

    public void ajouterPomsDans(int nbPoms,ViewGroup container){
        for(int i=0; i<nbPoms; i++){
            View pom = LayoutInflater.from(this).inflate(R.layout.component_pom, container, false);
            pom.setOnTouchListener(new MyTouchListener());
            container.addView(pom);
        }
    }

    public void reInit(){
        ViewGroup panier = findViewById(R.id.panier);
        ViewGroup zoneJeu =findViewById(R.id.zoneJeu);
        View pom=panier.findViewWithTag("pomme");
        while(pom!=null){
            panier.removeView(pom);
            pom=panier.findViewWithTag("pomme");
        }
        majCmpt(panier);
        zoneJeu.removeAllViews();
        ajouterPomsDans(moteur.getStock(),zoneJeu);
    }

    public void majCmpt(ViewGroup container){
        int count = container.getChildCount();
        //Cherche un endroit pour afficher le nombre de poms' contenues.
        TextView cmpt = container.findViewWithTag("cmpt");
        /*if(cmpt !=null) {
            //On prend "count-1" car le TextView est considéré comme un enfant.
            cmpt.setText("Contient " + (count - 1) + " poms'.");
        }*/
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

    class PomDragListener implements View.OnDragListener {

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

    //Seulement pour les tests.
    public DragAndDrop getMoteur(){
        return moteur;
    }

    public int getCodeJeu() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(menu);
    }
}
