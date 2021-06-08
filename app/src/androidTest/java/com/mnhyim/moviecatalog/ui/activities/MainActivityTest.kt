package com.mnhyim.moviecatalog.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.utils.EspressoIdlingResource.idlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun close() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun mainRecyclerViewTest() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(swipeUp())

        onView(withText("TV Show"))
            .perform(click())
        onView(withId(R.id.rv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_shows))
            .perform(swipeUp())
    }

    @Test
    fun movieDetailTest() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.img_bg))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name))
            .check(matches(isDisplayed()))

        pressBack()
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showDetailTest() {
        onView(withText("TV Show"))
            .perform(click())
        onView(withId(R.id.rv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.img_bg))
            .check(matches(isDisplayed()))
        onView(withId(R.id.img_detail_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_score))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name))
            .check(matches(isDisplayed()))

        pressBack()
        onView(withId(R.id.rv_shows))
            .check(matches(isDisplayed()))
    }
}