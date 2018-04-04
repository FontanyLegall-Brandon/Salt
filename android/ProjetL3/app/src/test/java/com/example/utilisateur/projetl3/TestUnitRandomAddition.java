package com.example.utilisateur.projetl3;

import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomAddition;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by theo on 4/4/18.
 */

public class TestUnitRandomAddition {
    public void testIfCorrect() {
        RandomAddition test = new RandomAddition();
        while (!test.getCorrect()) {//tant qu'on tombe sur une égalité fausse
            test = new RandomAddition();//on en prend une nouvelle
        }
        assertEquals(test.getA() + test.getB(), test.getC());
    }

    public void testIfIncorrect() {
        RandomAddition test = new RandomAddition();
        while (test.getCorrect()) {
            test = new RandomAddition();
        }
        assertNotEquals(test.getA() + test.getB(), test.getC());
    }
}
