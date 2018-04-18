package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

/**
 * Created by theo on 4/4/18.
 */

public enum Operation {
    ADDITION, SOUSTRACTION, MULTIPLICATION, COMPARAISON;

    public RandomEquality getRandomEquality() {
        RandomEquality res;
        if (this == ADDITION) {
            res = new RandomAddition();
        } else if (this == SOUSTRACTION) {
            res = new RandomSoustraction();
        } else if (this == MULTIPLICATION){
            res = new RandomMultiplication();
        } else {
            res = new RandomComp();
        }
        return res;
    }
}
