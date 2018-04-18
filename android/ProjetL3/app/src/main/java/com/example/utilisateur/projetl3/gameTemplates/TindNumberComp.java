package com.example.utilisateur.projetl3.gameTemplates;

import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.Operation;
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.TindNumber;

/**
 * Created by theo on 4/18/18.
 */

public class TindNumberComp extends TindNumber {
    public TindNumberComp() {
        super(Operation.COMPARAISON);
    }

    public int getCodeJeu() {
        return 3;
    }
}
