package com.example.utilisateur.projetl3.gameTemplates;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utilisateur.projetl3.R;

/**
 * Created by Utilisateur on 01/03/2018.
 */

public class PomDragAndDrop extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pom_drag_and_drop);
        findViewById(R.id.pomme).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.panier).setOnDragListener(new MyDragListener());
        findViewById(R.id.zoneJeu).setOnDragListener(new MyDragListener());
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
                    emplacementActu.removeView(pom);
                    ViewGroup container = (ViewGroup) v;
                    container.addView(pom);
                    pom.setVisibility(View.VISIBLE);
                    //On met à jour l'ancien emplacement de l'objet droppé ainsi que le nouveau.
                    majCmpt(emplacementActu);
                    majCmpt(container);

                    break;
                /*case DragEvent.ACTION_DRAG_ENDED:
                    //Se déclenche quand on termine le drop (n'est pas semblable au précédent, il se déclenche une fois toutes les opérations du drop terminées.)
                    break;*/
                default:
                    break;
            }
            return true;
        }
    }
}
