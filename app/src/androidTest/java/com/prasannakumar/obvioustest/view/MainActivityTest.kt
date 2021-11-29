package com.prasannakumar.obvioustest.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.prasannakumar.obvioustest.R
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

const val LIST_ITEMS = 16
const  val TITLE="Apollo 17's Moonship"
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase() {

    @get:Rule
    @Rule
    @JvmField
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    //RecyclerView  view comes into view
    @Test
    fun test_isListActivity_onAppLunch() {
        onView(withId(R.id.nasa_pics)).check(matches(isDisplayed()))
    }

    //select item and go to detail item
    @Test
    fun testisDetail_activity_is_visible() {
        onView(withId(R.id.nasa_pics)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                LIST_ITEMS,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
    }
    @Test
    fun test_backNavigation_to_MainActivity() {
        onView(withId(R.id.nasa_pics)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                LIST_ITEMS,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
        pressBack()
        onView(withId(R.id.nasa_pics)).check(matches(isDisplayed()))
    }
}