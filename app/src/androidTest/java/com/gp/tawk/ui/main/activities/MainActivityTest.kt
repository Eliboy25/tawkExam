package com.gp.tawk.ui.main.activities


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.gp.tawk.R
import kotlinx.android.synthetic.main.layout_view_toolbar.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule:ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
    }
    @Test
    fun type_search(){
        onView(withId(R.id.rvUsers)).check(matches(isDisplayed()))
    }
}
