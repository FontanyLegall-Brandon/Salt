package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

import java.util.Random;

/**
 * Created by theo on 4/3/18.
 */

public abstract class RandomEquality {
    private boolean correct;//definit si l'égalité est correcte ou non
    private int a;//a + b = c
    private int b;
    private int c;

    public RandomEquality() {//définir les constantes a, b et c
        Random r = new Random();
        this.correct = r.nextBoolean();
        this.a = 1;
        this.c = 0;
        while (this.a > this.c) {//on cherche a et c tels que a <= c
            this.a = r.nextInt(15);
            this.c = r.nextInt(15);
        }
    }

    public boolean getCorrect() {
        return correct;
    }

    public String getStringRepr(String op) {
        return a + " " + op + " " + b + " = " + c;
    }

    public int getA() {
        return a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void switchAC() {
        int temp = this.a;
        this.a = this.c;
        this.c = temp;
    }
}
