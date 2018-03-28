package com.example.utilisateur.projetl3;

/**
 * Created by Utilisateur on 28/03/2018.
 */
import com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.DragAndDrop;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DragAndDropTestUnit {
    @Test
    public void dragAndDrop_testDefaultConstructor() {
        for(int i=0; i<20; i++) {
            DragAndDrop moteur = new DragAndDrop();
            int goal = moteur.getGoal();
            assertTrue(goal >= 0 && goal <= 10);

            int valDepart = moteur.getValeurDepart();
            assertTrue(valDepart >= 0 && valDepart <= goal + 5);

            int stock = moteur.getStock();
            assertTrue(stock >= goal - valDepart && stock <= goal + 5);
        }
    }

    @Test
    public void dragAndDrop_testConstructor() {
        for(int i=0; i<20; i++) {
            DragAndDrop moteur = new DragAndDrop(3);
            int goal = moteur.getGoal();
            assertTrue(goal == 3);

            int valDepart = moteur.getValeurDepart();
            assertTrue(valDepart >= 0 && valDepart <= goal + 5);

            int stock = moteur.getStock();
            assertTrue(stock >= goal - valDepart && stock <= goal + 5);
        }
    }

    @Test
    public void dragAndDrop_testWin() {
        DragAndDrop moteur = new DragAndDrop(3);
        assertTrue(moteur.verifWin(3)==1);
        assertTrue(moteur.verifWin(2)==-1);
        assertTrue(moteur.verifWin(4)==0);
    }
}
