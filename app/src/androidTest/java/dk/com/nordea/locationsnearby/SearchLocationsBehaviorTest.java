package dk.com.nordea.locationsnearby;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by erdal on 1.2.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchLocationsBehaviorTest {

    private String query;

    @Rule
    public ActivityTestRule<SearchActivity> activityTestRule = new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void specifyQuery(){
        query = "Domino";
    }

    @Test
    public void fillText_getResults(){
        onView(withId(R.id.searchView)).perform(typeText(query));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException iex){
            // do nothing
        }

        onView(withId(R.id.listView)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView listView = (ListView) view;
                return listView.getCount() > 0;
            }

            @Override
            public void describeTo(Description description) {
                // do nothing
            }
        }));

        //onData(instanceOf(FSVenueItem.class)).inAdapterView(allOf(withId(R.id.listView), isDisplayed())).atPosition(1).check(matches(isDisplayed()));
    }
}
