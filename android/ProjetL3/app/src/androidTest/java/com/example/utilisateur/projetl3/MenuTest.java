package com.example.utilisateur.projetl3;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.utilisateur.projetl3.gameTemplates.PomDragAndDrop;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuTest {

    @Rule
    public IntentsTestRule<Menu> rule = new IntentsTestRule<Menu>(Menu.class,true,true);

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
    public void playButton()
    {
        onView(withId(R.id.playbutton))
                .perform(click());


        intended(hasComponent(MenuPrincipal.class.getName()));

    }

    @Test
    public void registerTextView()
    {
        onView(withId(R.id.tvSinscrireici))
                .perform(click());


        intended(hasComponent(RegisterActivity.class.getName()));

    }

    @Test
    public void question_registerTextView() {
        String m = rule.getActivity().getResources().getString(R.string.textView6);
        onView(withId(R.id.textView6)).check(matches(withText(m)));
    }


    @Test
    public void idTextView() {
        String m = rule.getActivity().getResources().getString(R.string.identifiants);
        onView(withId(R.id.textView)).check(matches(withText(m)));
    }
}