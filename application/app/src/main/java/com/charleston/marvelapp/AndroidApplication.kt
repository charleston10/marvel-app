package com.charleston.marvelapp

import com.charleston.data.DataModule
import com.charleston.domain.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

open class AndroidApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@AndroidApplication)
            AppModule.loadModule()
            DomainModule.loadModule()
            DataModule.loadModule()
            androidLogger()
        }
    }
}