package com.example.utilisateur.projetl3;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;

/*

        TODO : Continuer les tests, les tests actuels sont trés incomplets voire à peine commencés.

 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PomDragAndDropTest {

    //private String mStringToBetyped;

    @Rule
    public ActivityTestRule<PomDragAndDrop> mActivityRule = new ActivityTestRule<>(
            PomDragAndDrop.class);

    /*@Before
    public void init() {
    }*/

    @Test
    public void simpleClicOnPom_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount() - 1;
        int nbPomJeu = zoneJeu.getChildCount();

        /*
            TODO :
            Une fois qu'on aura un GridLayout on pourra ce baser sur cet exemple pour tester.
            onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.grid_adapter_id))
                .atPosition(0)
                .perform(click());

         */

        //TODO : Remettre une fois la différenciation des différents objets à tags possible. (sans AmbiguousViewMatcherException)
        //onView(withTagValue(is((Object) "pomme"))).perform(click());

        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount() - 1);
    }

    @Test
    public void simpleDragOnPom_noGoal_sameActivity() {
        //TODO
    }

    @Test
    public void simpleDragOnPom_withZoneJeuAsGoal_sameActivity() {
        //TODO
    }

    @Test
    public void simpleDragOnPom_withPanierAsGoal_sameActivity() {
        //TODO
    }

    @Test
    public void simpleClicOnReset_initState_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount() - 1;
        int nbPomJeu = zoneJeu.getChildCount();

        onView(withId(R.id.resetButton)).perform(click());

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien les mêmes qu'au départ.
        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount() - 1);
    }

    @Test
    public void simpleClicOnReset_notInitState_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount() - 1;
        int nbPomJeu = zoneJeu.getChildCount();

        //Bouger une pomme dans le panier avant de cliquer.
        //TODO

        onView(withId(R.id.resetButton)).perform(click());

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien différentes qu'au départ.
        //TODO : Remettre une fois les drag&drop effectués.
        //assertFalse(nbPomJeu== zoneJeu.getChildCount());
        //assertFalse(nbPomPan == panier.getChildCount() - 1);
        //... Mais qu'on a toujours autant de poms.
        assertEquals(nbPomJeu+nbPomPan,zoneJeu.getChildCount()+panier.getChildCount() - 1);
    }

    @Test
    public void simpleClicOnValid_initState() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount() - 1;
        int nbPomJeu = zoneJeu.getChildCount();
        boolean win=false;

        if(mActivityRule.getActivity().getMoteur().verifWin(nbPomPan)==1){
            win=true;
        }

        onView(withId(R.id.validButton)).perform(click());

        //Attention, le joueur peut parfois gagner en faisant ça, l'activité se fermera dans ce cas.
        if(win){
            //On vérifie que l'activité s'est fermée.
            assertTrue(mActivityRule.getActivity().isFinishing());
        }else{
            //On vérifie que si nombre de pommes dans le panier n'a pas changé, si il n'a pas changé alors il ne peut pas valider la condition.
            assertEquals(nbPomJeu, zoneJeu.getChildCount());
            assertEquals(nbPomPan, panier.getChildCount() - 1);
        }


    }

    @Test
    public void simpleClicOnValid_notInitState() {
        //Bouger une pomme dans le panier avant de cliquer.
        onView(withId(R.id.validButton)).perform(click());
        //Attention, le joueur peut parfois gagner en faisant ça, l'activité se fermera dans ce cas.

        //On vérifie ensuite que si nombre de pommes dans le panier n'est en effet PAS le nombre de pommes demandées.
        //Ou, si l'activité actuelle s'est fermée, on vérifie que le nombre de pommes avant le clic est bien celui demandé.
    }


    //Copie de "onTouchEvent" vu en cours.
    // On pourrait le modifier afin de réaliser des tests pour le drag&drop.
    /*public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            // debut du touch(on baisse le doigt)
            dessin = new ArrayList<>();
        }if ((e.getAction() == MotionEvent.ACTION_MOVE) || (e.getAction() == MotionEvent.ACTION_DOWN)) {
            // on déplace le doigt
            if (dessin != null) {
                Coord p = new Coord(e.getX(), e.getY());
                dessin.add(p);
            }
        }elseif (e.getAction() == MotionEvent.ACTION_UP) {
            // on lève le doigt
            dessinFini= (ArrayList<Coord>) dessin.clone();
            dessin = null;
        }
        invalidate();
        return true;
        // pour avoir les déplacements

    }*/
}