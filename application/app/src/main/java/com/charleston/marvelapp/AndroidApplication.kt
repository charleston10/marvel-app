package com.charleston.marvelapp

import com.charleston.data.DataModule
import com.charleston.data.remote.network.UrlProvider
import com.charleston.domain.DomainModule
import com.charleston.marvelapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class AndroidApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            loadModules()
        }
    }

    fun loadModules(urlProvider: UrlProvider? = null) {
        AppModule.loadModule()
        DomainModule.loadModule()
        DataModule.loadModule()
        DataModule.loadNetworkModule(urlProvider)
    }
}