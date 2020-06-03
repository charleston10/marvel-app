package com.charleston.marvelapp.vivewaction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    private val matcher: Matcher<Int> = `is`(expectedCount)

    @Throws(NoMatchingViewException::class)
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        recyclerView.adapter?.let {
            println("list count ${it.itemCount} and expected $expectedCount")
            assertThat(it.itemCount, matcher)
        }
    }
}