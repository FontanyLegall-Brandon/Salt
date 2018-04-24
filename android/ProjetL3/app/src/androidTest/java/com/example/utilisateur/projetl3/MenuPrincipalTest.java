package com.example.utilisateur.projetl3;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ProgressBar;

import com.example.utilisateur.projetl3.Aides.AideActivity;
import com.example.utilisateur.projetl3.Aides.ListeAides;
import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberAdd;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberComp;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberMul;
import com.example.utilisateur.projetl3.gameTemplates.TindNumberSub;
import com.example.utilisateur.projetl3.network.Singleton;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Created by inesr on 24/04/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MenuPrincipalTest {

    @Rule
    public IntentsTestRule<MenuPrincipal> rule = new IntentsTestRule<MenuPrincipal>(MenuPrincipal.class,true,true);

    @Before
    public void startTest() {
/*
        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("projet.projetm");
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
*/

    }

    @Test
    public void avancementText() {
        String m = rule.getActivity().getResources().getString(R.string.avancement);
        onView(withId(R.id.avancement)).check(matches(withText(m)));
    }

    @Test
    public void bienvenueTest() {
        String m = rule.getActivity().getResources().getString(R.string.bienvenue);
        onView(withId(R.id.bienvenue)).check(matches(withText(m)));
    }


    @Test
    public void pommesButton()
    {
        onView(withId(R.id.pommes))
                .perform(click());


        intended(hasComponent(PomDragAndDrop.class.getName()));

    }

   /* @Test
    public void addButton()
    {
        //ne peut pas etre testé tt comme sub mult et comp car il faut atteindre un certain score
        onView(withId(R.id.tindNumberAdd))
                .perform(click());


        intended(hasComponent(TindNumberAdd.class.getName()));

    }*/

    @Test
    public void aidesButton()
    {
        onView(withId(R.id.buttonAides))
                .perform(click());


        intended(hasComponent(ListeAides.class.getName()));

    }

    @Test
    public void refreshButton()
    {
        onView(withId(R.id.refresh))
                .perform(click());


        intended(hasComponent(MenuPrincipal.class.getName()));

    }


}
