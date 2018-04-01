package com.example.utilisateur.projetl3.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.utilisateur.projetl3.R;

/**
 * Cette View sera à utiliser/afficher en bas de chaque activity de type "jeu", elle permettra au joueur de valider ou non sa réponse.
 */

public class GameUI extends RelativeLayout {
    View rootView;
    View validButton;
    View resetButton;
    UIInteractionsListener listener; //On ne peut avoir qu'un seul listener pour le moment, il suffira d'en faire une liste si on a besoin de plusieurs listeners.

    public GameUI(Context context) {
        super(context);
        init(context);
    }

    public GameUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GameUI, 0, 0);

        //On considérera qu'on peut reload par défaut.
        Boolean canReload = a.getBoolean(R.styleable.GameUI_canReload,true);

        //Si on ne peut pas reload on supprime le bouton.
        if(!canReload){
            removeView(findViewById(R.id.resetButton));
        }

        a.recycle();
    }

    public void setUiListener(UIInteractionsListener listen){
        this.listener=listen;
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.game_ui, this);

        validButton = rootView.findViewById(R.id.validButton);
        resetButton = rootView.findViewById(R.id.resetButton);

        //On verra si par la suite on a des jeux qui n'ont pas besoin de bouton "valider", si oui on devra donner la possibilité de l'enlever.
        validButton.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (GameUI.this.listener != null) {
                    GameUI.this.listener.onUIInteraction("valider");
                }
                return false;
            }
        });

        //Tous les appareils android ont un bouton "retour" du coup en ajouter un dans l'UI ne devrait pas être necessaire.
        /*returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        resetButton.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (GameUI.this.listener != null) {
                    listener.onUIInteraction("recommencer");
                }
                return false;
            }
        });
    }
}
