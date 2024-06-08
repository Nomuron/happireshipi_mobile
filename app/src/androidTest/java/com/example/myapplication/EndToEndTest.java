package com.example.myapplication;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EndToEndTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void endToEndTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.addButton), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.addButton), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.addDishToList), withText("Dodaj"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.addDishToList), withText("Dodaj"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.list_icon), withContentDescription("Shopping list icon"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.main),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.RecipeDetailIngredientAmount), withText("200.0"),
                        withParent(withParent(withId(R.id.recyclerViewSelectedRecipesIngredientsList))),
                        isDisplayed()));
        textView.check(matches(withText("200.0")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.RecipeDetailIngredient), withText("ciecierzyca z puszki"),
                        withParent(withParent(withId(R.id.recyclerViewSelectedRecipesIngredientsList))),
                        isDisplayed()));
        textView2.check(matches(withText("ciecierzyca z puszki")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.selectedRecipeName), withText("Fondant czekoladowy"),
                        withParent(withParent(withId(R.id.recyclerViewSelectedRecipesList))),
                        isDisplayed()));
        textView3.check(matches(withText("Fondant czekoladowy")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.selectedPortionAmount), withText("3"),
                        withParent(withParent(withId(R.id.recyclerViewSelectedRecipesList))),
                        isDisplayed()));
        textView4.check(matches(withText("3")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
