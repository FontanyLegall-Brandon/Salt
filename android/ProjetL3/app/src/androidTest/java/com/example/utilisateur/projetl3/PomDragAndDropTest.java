package com.example.utilisateur.projetl3;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.MotionEvents;
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
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();


        onView(nthChildOf(withId(R.id.zoneJeu),0))
            .perform(click());

        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount());
    }

    @Test
    public void simpleDragOnPom_noGoal_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();

        onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.zoneJeu))
                .atPosition(0)
                .perform(touchDownAndUp(10, 10)); //Normalement il n'y a rien ici, ou si il y a quelquechose ce n'est pas un conteneur.

        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount());
    }

    @Test
    public void simpleDragOnPom_withZoneJeuAsGoal_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();

        onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.zoneJeu))
                .atPosition(0)
                .perform(touchDownAndUp(zoneJeu.getX(), zoneJeu.getY()));

        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount());
    }

    @Test
    public void simpleDragOnPom_withPanierAsGoal_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();

        onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.zoneJeu))
                .atPosition(0)
                .perform(touchDownAndUp(panier.getX(), panier.getY()));

        assertEquals(nbPomJeu-1, zoneJeu.getChildCount());
        assertEquals(nbPomPan+1, panier.getChildCount());
    }

    @Test
    public void simpleClicOnReset_initState_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();

        onView(withId(R.id.resetButton)).perform(click());

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien les mêmes qu'au départ.
        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount());
    }

    @Test
    public void simpleClicOnReset_notInitState_sameActivity() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
        int nbPomJeu = zoneJeu.getChildCount();

        //Bouger une pomme dans le panier avant de cliquer.
        onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.zoneJeu))
                .atPosition(0)
                .perform(touchDownAndUp(panier.getX(), panier.getY()));

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien différentes qu'au départ.
        assertFalse(nbPomJeu== zoneJeu.getChildCount());
        assertFalse(nbPomPan == panier.getChildCount());
        //... Mais qu'on a toujours autant de poms.
        assertEquals(nbPomJeu+nbPomPan,zoneJeu.getChildCount()+panier.getChildCount());

        onView(withId(R.id.resetButton)).perform(click());

        //Puis on vérifie que le reset a bien marché.
        assertEquals(nbPomJeu, zoneJeu.getChildCount());
        assertEquals(nbPomPan, panier.getChildCount());
    }

    @Test
    public void simpleClicOnValid_initState() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        int nbPomPan = panier.getChildCount();
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
            assertEquals(nbPomPan, panier.getChildCount());
        }


    }

    @Test
    public void simpleClicOnValid_notInitState() {
        ViewGroup zoneJeu = mActivityRule.getActivity().findViewById(R.id.zoneJeu);
        ViewGroup panier = mActivityRule.getActivity().findViewById(R.id.panier);

        //Bouger une pomme dans le panier avant de cliquer.
        onData(withTagValue(is((Object) "pomme")))
                .inAdapterView(withId(R.id.zoneJeu))
                .atPosition(0)
                .perform(touchDownAndUp(panier.getX(), panier.getY()));

        //Une fois la pomme bougée on effectue le même genre de tests que pour une validation simple.
        int nbPomPan = panier.getChildCount();
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
            assertEquals(nbPomPan, panier.getChildCount());
        }
    }


    public static ViewAction touchDownAndUp(final float x, final float y) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[] { x + location[0], y + location[1] };
                float[] precision = new float[] { 1f, 1f };

                // Send down event, pause, and send up
                MotionEvent down = MotionEvents.sendDown(uiController, coordinates, precision).down;
                uiController.loopMainThreadForAtLeast(200);
                MotionEvents.sendUp(uiController, down, coordinates);
            }
        };
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) return false;
                ViewGroup parent = (ViewGroup) view.getParent();

                return parentMatcher.matches(parent)
                        && parent.getChildCount() > childPosition
                        && parent.getChildAt(childPosition).equals(view);
            }
        };
    }
}