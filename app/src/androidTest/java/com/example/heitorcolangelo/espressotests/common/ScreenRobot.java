package com.example.heitorcolangelo.espressotests.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.view.View;

import com.example.heitorcolangelo.espressotests.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.heitorcolangelo.espressotests.common.actions.CallOnClickAction.callOnClick;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

@SuppressWarnings("unchecked")
public abstract class ScreenRobot <T extends ScreenRobot> {

    private Activity activityContext;

    public static <T extends ScreenRobot> T withRobot(Class<T> screenRobotClass) {
        if (screenRobotClass == null) {
            throw new IllegalArgumentException("instance class == null");
        }

        try {
            return screenRobotClass.newInstance();
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("IllegalAccessException", iae);
        } catch (InstantiationException ie) {
            throw new RuntimeException("InstantiationException", ie);
        }
    }

    public Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    public T sleep() throws InterruptedException {
        return sleep(1);
    }

    public T sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
        return (T) this;
    }

    public T checkIsDisplayed(@IdRes int... viewIds) {
        for (int viewId : viewIds) {
            onView(withId(viewId)).check(matches(isDisplayed()));
        }
        return (T) this;
    }

    public T checkIsClickable(@IdRes int... viewIds) {
        for (int viewId : viewIds) {
            onView(withId(viewId)).check(matches(isClickable()));
        }
        return (T) this;
    }

    public T checkIsHidden(@IdRes int... viewIds) {
        for (int viewId : viewIds) {
            onView(withId(viewId)).check(matches(not(isDisplayed())));
        }
        return (T) this;
    }

    public T checkDoesNotExist(@IdRes int... viewIds) {
        for (int viewId : viewIds) {
            onView(withId(viewId)).check(doesNotExist());
        }
        return (T) this;
    }

    public T checkViewHasText(@IdRes int viewId, @StringRes int messageResId) {
        onView(withId(viewId)).check(matches(withText(messageResId)));
        return (T) this;
    }

    public T checkViewHasDrawableAndTag(int imageResId) {
        onView(withTagValue(is((Object) imageResId))).check(matches(isDisplayed()));
        return (T) this;
    }

    public T scrollViewDown(@IdRes int viewIds) {
        onView(withId(viewIds)).perform(swipeUp(), click());
        return (T) this;
    }

    public T checkViewHasText(@IdRes int viewId, String expected) {
        onView(withId(viewId)).check(matches(withText(expected)));
        return (T) this;
    }

    public T scrollViewUp(@IdRes int viewIds) {
        onView(withId(viewIds)).perform(swipeDown(), click());
        return (T) this;
    }

    public T checkViewContainsText(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
        return (T) this;
    }

    public T checkViewHasHint(@IdRes int viewId, @StringRes String messageResId) {
        onView(withId(viewId)).check(matches(withHint(messageResId)));
        return (T) this;
    }

    public T callOnClickOnView(@IdRes int viewId) {
        // On small Views, click action isn't always detected.
        // To avoid this, use callOnClick().
        onView(withId(viewId)).perform(callOnClick());
        return (T) this;
    }

    public T clickOnView(@IdRes int viewId) {
        onView(withId(viewId)).perform(click());
        return (T) this;
    }

    public T pressBack() {
        Espresso.pressBack();
        return (T) this;
    }

    public T goBackFromToolbar() {
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        return (T) this;
    }

    public T closeKeyboard() {
        Espresso.closeSoftKeyboard();
        return (T) this;
    }

    public T pressImeAction(@IdRes int viewId) {
        onView(withId(viewId)).perform(pressImeActionButton());
        return (T) this;
    }

    public T assertItTakeMeToScreen(Class<?> targetClass) {
        intended(hasComponent(targetClass.getName()));
        return (T) this;
    }

    public T enterTextIntoView(@IdRes int viewId, String text) {
        onView(withId(viewId)).perform(typeText(text));
        closeKeyboard();
        return (T) this;
    }

    public T checkDialogWithTextIsDisplayed(@StringRes int messageResId) {
        onView(withText(messageResId))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        return (T) this;
    }

    public T checkDialogWithTextIsDisplayed(String message) {
        onView(withText(message))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        return (T) this;
    }

    public T swipeLeftOnView(@IdRes int viewId) {
        onView(withId(viewId)).perform(swipeLeft());
        return (T) this;
    }

    public T swipeRightOnView(@IdRes int viewId) {
        onView(withId(viewId)).perform(swipeRight());
        return (T) this;
    }

    public T clickOnCardForList(@IdRes int viewId, int position) {
        onView(withIndex(withId(viewId), position)).perform(click());
        return (T) this;
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}

