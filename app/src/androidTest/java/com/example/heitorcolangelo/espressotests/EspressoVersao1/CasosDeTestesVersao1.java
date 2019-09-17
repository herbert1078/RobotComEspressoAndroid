package com.example.heitorcolangelo.espressotests.EspressoVersao1;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.ui.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CasosDeTestesVersao1 {

    @Rule
    public ActivityTestRule<LoginActivity>
            mActivityRule = new ActivityTestRule<>(LoginActivity.class, false, true);

    @Before
    public void setup(){

    }

    @After
    public void finish(){


    }

    @Test
    public void PrimeiroTeste(){
        onView(withId(R.id.login_image)).check(matches(isDisplayed()));
        onView(withId(R.id.login_username)).check(matches(isDisplayed()));
        onView(withId(R.id.login_password)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        onView(withId(R.id.login_username)).check(matches(withHint("Type your username")));
        onView(withId(R.id.login_password)).check(matches(withHint("Type your password")));
        onView(withText("Espresso Tests")).check(matches(isDisplayed()));

    }

    @Test
    public void SegundoTeste(){
        onView(withId(R.id.login_button)).perform(click());
        onView(withText("Atenção")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Campo De Email Ou Senha Inválido")).inRoot(isDialog()).check(matches(isDisplayed()));

    }

    @Test
    public void TerceiroTeste(){

        onView(withId(R.id.login_username)).perform(typeText("herbert_799@hotmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withText("Atenção")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Campo De Email Ou Senha Inválido")).inRoot(isDialog()).check(matches(isDisplayed()));


    }

    @Test
    public void QuartoTeste(){
        onView(withId(R.id.login_password)).perform(typeText("teste1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withText("Atenção")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Campo De Email Ou Senha Inválido")).inRoot(isDialog()).check(matches(isDisplayed()));

    }

    @Test
    public void QuintoTeste(){
        onView(withId(R.id.login_username)).perform(typeText("herbert_799@hotmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText("teste1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withText("Lista de Usuarios")).check(matches(isDisplayed()));

    }

    @Test
    public void SextoTeste(){
        onView(withId(R.id.login_username)).perform(typeText("herbert_799@hotmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText("teste1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.login_image)).check(matches(isDisplayed()));
        onView(withId(R.id.login_username)).check(matches(isDisplayed()));
        onView(withId(R.id.login_password)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));

    }

    @Test
    public void SetimoTeste()throws InterruptedException {
        onView(withId(R.id.login_username)).perform(typeText("herbert_799@hotmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText("teste1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.recycler_view)).perform(swipeUp(), click());
        Thread.sleep(2000);
    }

}
