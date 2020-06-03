package com.charleston.marvelapp.config

import com.charleston.marvelapp.AndroidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinTestApplication : AndroidApplication() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@KoinTestApplication)
            loadModules(MockUrlProvider())
        }
    }
}