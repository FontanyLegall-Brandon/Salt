package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by theo on 4/18/18.
 */

public class RandomComp extends RandomEquality {

    private String inegalite;

    public RandomComp() {
        super();
        Random rand = new Random();

        int tempA = rand.nextInt(20);
        int tempB = rand.nextInt(20);
        int aleatSym;

        if (getCorrect()) {//permet de choisir le symbole adéquat
            if (tempA < tempB) {
                inegalite = "<";
            } else if (tempA == tempB) {
                inegalite = "=";
            } else {
                inegalite = ">";
            }
        } else {
            aleatSym = rand.nextInt(2);
            if (tempA < tempB) {
                if (aleatSym == 0) {//randomiser le symbol non valide affiché
                    inegalite = ">";
                } else {
                    inegalite = "=";
                }
            } else if (tempA == tempB) {
                if (aleatSym == 0) {
                    inegalite = ">";
                } else {
                    inegalite = "<";
                }
            } else {
                if (aleatSym == 0) {
                    inegalite = "<";
                } else {
                    inegalite = "=";
                }
            }
        }

        setA(tempA);
        setB(tempB);
    }

    public String toString() {
        return getA() + "\t\t" + inegalite + "\t\t" + getB();
    }
}
