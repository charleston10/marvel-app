package com.charleston.marvelapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.charleston.data.remote.network.HttpClient
import com.charleston.marvelapp.mocks.mockEventsJson
import com.charleston.marvelapp.rules.HttpIdlingResourceRule
import com.charleston.marvelapp.screens.main.MainActivity
import com.charleston.marvelapp.vivewaction.RecyclerViewItemCountAssertion
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject


class MainFragmentTest : KoinTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val server = MockWebServer()

    private val httpClient: HttpClient by inject()

    @get:Rule
    val idlingResource = HttpIdlingResourceRule(httpClient)

    @Before
    fun setup() {
        server.run {
            start(3604)
            url("/")
        }
    }

    @Test
    fun onLaunch_ThemesAndEvents_isDisplayed() {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockEventsJson(context))
        )

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.list_theme))
            .check(matches(isDisplayed()))
            .check(RecyclerViewItemCountAssertion(3))

        Thread.sleep(150)

        onView(withId(R.id.list_events))
            .check(matches(isDisplayed()))
            .check(RecyclerViewItemCountAssertion(30))
    }

    @Test
    fun onLaunchWithError_ServerBadRequest_isErrorDisplayed() {
        server.enqueue(
            MockResponse().setResponseCode(400)
        )

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchWithError_ServerInternalError_isErrorDisplayed() {
        server.enqueue(
            MockResponse().setResponseCode(500)
        )

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}