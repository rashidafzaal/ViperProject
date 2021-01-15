package com.example.vipersample

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class ExmapleUnitTest {

    @Test
    fun checkEditTextClick() {
        onView(withId(R.id.etSelectMovie)).perform(click())
    }
}