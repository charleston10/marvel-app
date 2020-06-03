package com.charleston.marvelapp.rules

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import com.charleston.data.remote.network.HttpClient
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


class HttpIdlingResourceRule(httpClient: HttpClient) : TestRule {

    private var resource: IdlingResource = OkHttp3IdlingResource
        .create("OkHttp", httpClient.provideOkHttpClient())

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                Espresso.registerIdlingResources(resource);
                base?.evaluate();
                Espresso.unregisterIdlingResources(resource);
            }
        }
    }
}