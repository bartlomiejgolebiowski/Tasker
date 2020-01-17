package t.golab.tasker;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.google.android.material.button.MaterialButton;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import t.golab.tasker.model.Task;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainActivityTest that = (MainActivityTest) o;
        return Objects.equals(activityRule, that.activityRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityRule);
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test_isActivityInView() {
        MainActivity.isTesting = true;
        onView(withId(R.id.main)).check(matches(isDisplayed()));

    }

    @Test
    public void test_isRecyclerView() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void test_button_isClickable_isEnabled_checkTexts(){
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).check(matches(withText(R.string.startWorking)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(2, R.id.button)).check(matches(not(isEnabled())));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.status)).check(matches(withText(Task.TRAVELLING)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.status)).check(matches(withText(Task.TRAVELLING)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).check(matches(withText(R.string.stop)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(2, R.id.button)).check(matches(not(isEnabled())));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.status)).check(matches(withText(Task.WORKING)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).perform(click());
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.button)).check(matches(withText(R.string.startTravelling)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(2, R.id.button)).check(matches(isEnabled()));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.status)).check(matches(withText(Task.OPEN)));
    }

}