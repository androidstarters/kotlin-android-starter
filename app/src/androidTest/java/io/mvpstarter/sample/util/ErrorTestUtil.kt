package io.mvpstarter.sample.util

import io.mvpstarter.sample.R

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.Matchers.allOf

object ErrorTestUtil {

    fun checkErrorViewsDisplay() {
        onView(allOf<View>(withText(R.string.error_title), isDisplayed()))
                .check(matches(isDisplayed()))
        onView(allOf<View>(withText(R.string.error_message), isDisplayed()))
                .check(matches(isDisplayed()))
    }

    fun checkClickingReloadShowsContentWithText(expectedText: String) {
        onView(allOf<View>(withText(R.string.error_reload), isDisplayed()))
                .perform(click())
        onView(withText(expectedText))
                .check(matches(isDisplayed()))
    }

}
