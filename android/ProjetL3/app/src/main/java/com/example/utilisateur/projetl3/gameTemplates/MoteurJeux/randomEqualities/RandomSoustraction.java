package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by theo on 4/4/18.
 */

public class RandomSoustraction extends RandomEquality {
    public RandomSoustraction() {
        super();
        Random r = new Random();
        if (this.getA() < this.getC()) this.switchAC();
        int tempB = this.getA() - this.getC();//calcul de la valeur de b

        if (!this.getCorrect()) {
            //si l'équation n'est pas correcte, on la fausse
            tempB += r.nextInt(10) + 1;//on ajoute 1 pour ne pas avoir 0, qui rendrait l'équation vraie
        }

        this.setB(tempB);
    }

    public String toString() {
        return this.getStringRepr("-");
    }
}
