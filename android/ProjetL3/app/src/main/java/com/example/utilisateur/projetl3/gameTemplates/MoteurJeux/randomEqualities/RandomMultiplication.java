package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by theo on 4/4/18.
 */

public class RandomMultiplication extends RandomEquality {
    public RandomMultiplication() {
        super();
        Random r = new Random();
        int tempA;
        int tempB;
        int tempC;

        tempA = r.nextInt(10);
        tempB = r.nextInt(10);
        tempC = tempA * tempB;

        if (!this.getCorrect()) {
            //si l'équation n'est pas correcte, on la fausse
            tempB += r.nextInt(5) + 1;//on ajoute 1 pour ne pas avoir 0, qui rendrait l'équation vraie
            while (tempA * tempB == tempC) {
                tempA++;
            }
        }

        this.setA(tempA);
        this.setB(tempB);
        this.setC(tempC);
    }

    public String toString() {
        return this.getStringRepr("*");
    }
}
