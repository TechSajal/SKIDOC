package com.example.humandisclass

import ToastMatcher
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.humandisclass.Activity.LandingActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EndToEndTesting {

    private  lateinit var scenario: ActivityScenario<LandingActivity>

    @Before
    fun setup(){
        scenario = ActivityScenario.launch(LandingActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun TestEndToEnd() {
        onView(withId(R.id.textinputedittextstarted)).perform(click())
        val email = "sajalkaushal3@gmail.com"
        val pass = "SKsajal@5"
        //espresso
        onView(withId(R.id.textInput_Email_login)).perform(typeText(email))
        onView(withId(R.id.text_input_password_login)).perform(typeText(pass))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.login_continue)).perform(doubleClick())
        onView(withText("Welcome")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}