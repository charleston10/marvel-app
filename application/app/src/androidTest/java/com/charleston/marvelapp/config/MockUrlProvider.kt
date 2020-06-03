package com.charleston.marvelapp.config

import com.charleston.data.remote.network.UrlProvider

class MockUrlProvider : UrlProvider() {

    override fun getUrl(): String {
        return "http://localhost:3604/"
    }
}