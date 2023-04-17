package com.example.aaoj

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        // UiDevice インスタンスを取得。
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun mainActivityTest() {
        val edittext = onView(withId(R.id.edittext_pid))
        edittext.perform(typeText("0123"))

        val cond = Until.hasObject(By.res("com.example.AAOJ", "button_search"))

        device.wait(cond, 1000L)

        val button = onView(withId(R.id.button_search))
        button.perform(click())

        device.wait(cond, 2000L)

        edittext.check(matches(isDisplayed()))
    }

    @Test
    fun writeSurface() {
        onView(withId(R.id.fab_change)).perform(click())

        onView(withId(R.id.surfaceView)).perform(swipeDown())
        onView(withId(R.id.surfaceView)).perform(swipeLeft())

        val cond = Until.hasObject(By.res("com.example.AAOJ", "surfaceView"))
        device.wait(cond, 2000L)
    }
}