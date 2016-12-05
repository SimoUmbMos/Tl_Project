package com.tlproject.omada1.tl_project.Activitys;


import android.app.Instrumentation;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.tlproject.omada1.tl_project.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegistTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registTest() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(MapsActivity.class.getName(), null, false);

        onView(allOf(withId(R.id.RegistEmail), withText("Regist with Email"),
                withParent(allOf(withId(R.id.SignInMenu), withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.etEmail), withParent(allOf(withId(R.id.EmailAction),
                withParent(withId(R.id.activity_main)))), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.etEmail), withParent(allOf(withId(R.id.EmailAction),
                withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(replaceText("test123@test123.com"), closeSoftKeyboard());


        onView(allOf(withId(R.id.etPassword), withParent(allOf(withId(R.id.EmailAction),
                withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(replaceText("test123"), closeSoftKeyboard());

        onView(allOf(withId(R.id.etPassword), withText("test123"),
                withParent(allOf(withId(R.id.EmailAction), withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(pressImeActionButton());

        onView(allOf(withId(R.id.etUsername), withParent(allOf(withId(R.id.EmailAction),
                withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(replaceText("test123"), closeSoftKeyboard());

        onView(allOf(withId(R.id.btProceed), withText("Regist"),
                withParent(allOf(withId(R.id.EmailAction), withParent(withId(R.id.activity_main)))),
                isDisplayed())).perform(click());

        MapsActivity nextActivity = (MapsActivity) getInstrumentation()
                .waitForMonitorWithTimeout(activityMonitor, 5000);

        ViewInteraction textView = onView(allOf(withId(R.id.usernamedisp), withText("test123"),
                        isDisplayed()));

        textView.check(matches(withText("test123")));
        assertNotNull(nextActivity);

        onView(allOf(withId(R.id.btlogout), withParent(allOf(withId(R.id.activity_map),
                withParent(withId(android.R.id.content)))), isDisplayed())).perform(click());
    }
}
