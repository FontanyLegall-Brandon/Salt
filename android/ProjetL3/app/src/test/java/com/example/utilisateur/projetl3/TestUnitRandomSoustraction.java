package com.example.utilisateur.projetl3;

import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomSoustraction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by theo on 4/4/18.
 */

public class TestUnitRandomSoustraction {

    @Test
    public void testIfCorrect() {
        RandomSoustraction test = new RandomSoustraction();
        while (!test.getCorrect()) {//tant qu'on tombe sur une égalité fausse
            test = new RandomSoustraction();//on en prend une nouvelle
        }
        assertEquals(test.getA() - test.getB(), test.getC());
    }

    @Test
    public void testIfIncorrect() {
        RandomSoustraction test = new RandomSoustraction();
        while (test.getCorrect()) {
            test = new RandomSoustraction();
        }
        assertNotEquals(test.getA() - test.getB(), test.getC());
    }
}
