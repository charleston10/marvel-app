package com.charleston.marvelapp

import com.charleston.data.remote.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

open class AndroidApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(applicationContext)
            NetworkModule.loadModule()
            androidLogger()
        }
    }
}