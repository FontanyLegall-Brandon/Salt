package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by theo on 4/4/18.
 */

public class RandomAddition extends RandomEquality {

    public RandomAddition() {
        super();
        Random r = new Random();
        int tempB = this.getC() - this.getA();//calcul de la valeur de b

        if (!this.getCorrect()) {

            tempB += r.nextInt(10) + 1;//si l'équation n'est pas correcte, on la fausse -- on ajoute 1 pour ne pas avoir 0, qui rendrait l'équation vraie
        }

        this.setB(tempB);
    }

    public String toString() {
        return this.getStringRepr("+");
    }
}
