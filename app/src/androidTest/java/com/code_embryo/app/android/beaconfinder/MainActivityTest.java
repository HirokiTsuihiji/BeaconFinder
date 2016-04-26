package com.code_embryo.app.android.beaconfinder;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import com.code_embryo.android.ble.beacon.Beacon;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
  private Activity activity;

  @Rule
  public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

  @Before
  public void setUp() {
    activity = mActivityRule.getActivity();
  }
  @Test
  public void clickScanSwitch() {
    onView(withId(R.id.switchScan)).perform(click());
    onView(withId(R.id.switchScan)).check(matches(isChecked()));

    onView(withId(R.id.switchScan)).perform(click());
    onView(withId(R.id.switchScan)).check(matches((isNotChecked())));
  }

  @Test
  public void openSortDialog() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText(R.string.sort_title)).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText("デバイスアドレス")).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText("デバイスアドレス（降順）")).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText("UUID + Major + Minor")).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText("UUID + Major + Minor（降順）")).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText(R.string.ok)).inRoot(isDialog()).check(matches(isDisplayed()));
    onView(withText(R.string.cancel)).inRoot(isDialog()).check(matches(isDisplayed()));

    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withText(R.string.sw_scan)).check(matches(isDisplayed()));
  }

  @Test
  public void sortDeviceAddress() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス")).inRoot(isDialog()).check(matches(isChecked()));
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());
  }

  @Test
  public void sortDeviceAddressReverse() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス（降順）")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス（降順）")).inRoot(isDialog()).check(matches(isChecked()));
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());
  }

  @Test
  public void sortBeaconId() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText("UUID + Major + Minor")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("UUID + Major + Minor")).inRoot(isDialog()).check(matches(isChecked()));
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());
  }

  @Test
  public void sortBeaconIdReverse() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText("UUID + Major + Minor（降順）")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("UUID + Major + Minor（降順）")).inRoot(isDialog()).check(matches(isChecked()));
    onView(withText(R.string.ok)).perform(click());
  }

  @Test
  public void canCancelSortOrder() {
    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("UUID + Major + Minor（降順）")).inRoot(isDialog()).perform(click());
    onView(withText(R.string.cancel)).inRoot(isDialog()).perform(click());

    onView(withId(R.id.sort)).perform(click());

    onView(withText("デバイスアドレス")).inRoot(isDialog()).check(matches(isChecked()));
    onView(withText(R.string.ok)).inRoot(isDialog()).perform(click());
  }

  @Test
  public void testListView() throws InterruptedException, NoSuchFieldException {
    onView(withId(R.id.switchScan)).perform(click());

    ListView listView = (ListView) activity.findViewById(R.id.listView);
    IdlingResource idlingResource = new ListViewLoader(listView);
    Espresso.registerIdlingResources(idlingResource);

    onData(withBeacon(is("00:1C:4D:40:ED:78"),
            is("00000000E6B51001B000001C4D1A9E0A"), is(2), is(1))).check(matches(isDisplayed()));

    Espresso.unregisterIdlingResources(idlingResource);
  }

  private static Matcher<Object> withBeacon(final Matcher<String> deviceAddressMatcher,
                                            final Matcher<String> uuidMatcher,
                                            final Matcher<Integer> majorMatcher,
                                            final Matcher<Integer> minorMatcher) {
    return new BoundedMatcher<Object, Beacon>(Beacon.class) {
      @Override
      protected boolean matchesSafely(Beacon item) {
        return deviceAddressMatcher.matches(item.deviceAddress())
                && uuidMatcher.matches(item.uuid())
                && majorMatcher.matches(item.major())
                && minorMatcher.matches(item.minor());
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Device Address: ");
        description.appendText("UUID: ");
        description.appendText("Major: ");
        description.appendText("Minor: ");

        deviceAddressMatcher.describeTo(description);
        uuidMatcher.describeTo(description);
        majorMatcher.describeTo(description);
        minorMatcher.describeTo(description);
      }
    };
  }
}