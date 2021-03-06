package com.prasannakumar.obvioustest.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.prasannakumar.obvioustest.R
import com.prasannakumar.obvioustest.util.Utils
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


const val LIST_ITEMS = 16
const val TITLE = "Apollo 17's Moonship"
const val JSON = "nasa.json"
const val JSON_EXAMPLE = "sample test"

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase() {

    lateinit var instrumentationContext: Context

    @Rule
    @JvmField
    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

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

    @Test
    fun useRunBlocking_if_data_is_same() = runBlocking<Unit> {
        val util = Utils(instrumentationContext)
        val expected = util.getJsonFromAssets(JSON)
        val result = util.getJsonFromAssets(JSON)
        assertTrue(expected == result)
    }

    @Test
    fun useRunBlocking_if_data_is_not_same() = runBlocking<Unit> {
        val util = Utils(instrumentationContext)
        val expected = JSON_EXAMPLE
        val result = util.getJsonFromAssets(JSON)
        assertFalse(expected == result)
    }
}


