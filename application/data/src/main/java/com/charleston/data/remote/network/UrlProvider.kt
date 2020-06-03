package com.charleston.data.remote.network

import com.charleston.data.BuildConfig

open class UrlProvider {

    open fun getUrl(): String {
        return BuildConfig.BASE_URL
    }
}