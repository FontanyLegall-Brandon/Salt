package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by inesr on 17/04/2018.
 */

public class RandomInf extends RandomComparison {
// cest a < b
    public RandomInf() {
        super();
        Random r = new Random();
        int tempC = this.getB() - this.getA();//calcul de la valeur de b

        if (!this.getCorrect()) {
            //si l'équation n'est pas correcte, on la fausse
            tempC += r.nextInt(10) + 1;//on ajoute 1 pour ne pas avoir 0, qui rendrait l'équation vraie
        }

        this.setB(tempC);
    }

    public String toString() {
        return this.getStringRepr("<");
    }
}

