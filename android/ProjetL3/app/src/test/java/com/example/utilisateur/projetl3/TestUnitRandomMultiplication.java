package com.example.utilisateur.projetl3;

import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities.RandomMultiplication;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by theo on 4/4/18.
 */

public class TestUnitRandomMultiplication {

    @Test
    public void testIfCorrect() {
        RandomMultiplication test = new RandomMultiplication();
        while (!test.getCorrect()) {//tant qu'on tombe sur une égalité fausse
            test = new RandomMultiplication();//on en prend une nouvelle
        }
        assertEquals(test.getA() * test.getB(), test.getC());
    }

    @Test
    public void testIfIncorrect() {
        RandomMultiplication test = new RandomMultiplication();
        while (test.getCorrect()) {
            test = new RandomMultiplication();
        }
        assertNotEquals(test.getA() * test.getB(), test.getC());
    }
}
