package com.example.utilisateur.projetl3;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/*

        TODO : Continuer les tests, les tests actuels sont trés incomplets voire à peine commencés.

 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PomDragAndDropTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<PomDragAndDrop> mActivityRule = new ActivityTestRule<>(
            PomDragAndDrop.class);

    /*@Before
    public void init() {
    }*/

    @Test
    public void simpleClicOnPom_sameActivity() {
        /*
            Exemple d'utilisation de espresso

        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(mStringToBetyped)));
        */
    }

    @Test
    public void simpleDragOnPom_noGoal_sameActivity() {
    }

    @Test
    public void simpleDragOnPom_withZoneJeuAsGoal_sameActivity() {
    }

    @Test
    public void simpleDragOnPom_withPanierAsGoal_sameActivity() {
    }

    @Test
    public void simpleClicOnReset_initState_sameActivity(){
        onView(withId(R.id.resetButton)).perform(click());

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien les mêmes qu'au départ.
    }

    @Test
    public void simpleClicOnReset_notInitState_sameActivity(){
        //Bouger une pomme dans le panier avant de cliquer.
        onView(withId(R.id.resetButton)).perform(click());

        //On vérifie ensuite que le nombre de pommes dans la zone de jeu et dans le panier sont bien les mêmes qu'au départ.
    }

    @Test
    public void simpleClicOnValid_initState(){
        onView(withId(R.id.validButton)).perform(click());
        //Attention, le joueur peut parfois gagner en faisant ça, l'activité se fermera dans ce cas.

        //On vérifie ensuite que si nombre de pommes dans le panier n'est en effet PAS le nombre de pommes demandées.
        //Ou, si l'activité actuelle s'est fermée, on vérifie que le nombre de pommes avant le clic est bien celui demandé.
    }

    @Test
    public void simpleClicOnValid_notInitState(){
        //Bouger une pomme dans le panier avant de cliquer.
        onView(withId(R.id.validButton)).perform(click());
        //Attention, le joueur peut parfois gagner en faisant ça, l'activité se fermera dans ce cas.

        //On vérifie ensuite que si nombre de pommes dans le panier n'est en effet PAS le nombre de pommes demandées.
        //Ou, si l'activité actuelle s'est fermée, on vérifie que le nombre de pommes avant le clic est bien celui demandé.
    }
}