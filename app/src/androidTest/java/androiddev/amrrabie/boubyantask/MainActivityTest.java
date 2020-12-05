package androiddev.amrrabie.boubyantask;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androiddev.amrrabie.boubyantask.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    /*@Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }*/

    @Test
    public void testLaunch(){
        View v=mActivity.findViewById(R.id.articlesrecycler);
        assertNotNull(v);
    }

    @Test
    public void testLaunch2(){
        View v=mActivity.findViewById(R.id.periodspinner);
        assertNotNull(v);
    }

    @Test
    public void testClick(){
        onView(withId(R.id.periodspinner)).perform(click());
    }

   /* @Test
    public void matchString(){
        onView(withId(R.id.periodspinner)).check(matches(withText("1 Day")));
    }*/
}
