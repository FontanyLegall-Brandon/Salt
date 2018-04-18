package com.example.utilisateur.projetl3.gameTemplates;

import android.content.Intent;
import android.view.View;

import com.example.utilisateur.projetl3.MenuPrincipal;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.Operation;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.TindNumber;

/**
 * Created by theo on 4/4/18.
 */

public class TindNumberMul extends TindNumber {

    public TindNumberMul() {
        super(Operation.MULTIPLICATION);
    }

    public int getCodeJeu() {
        return 3;
    }
}
