package com.charleston.data

import com.charleston.data.local.MarvelLocal
import com.charleston.data.remote.MarvelCloud
import com.charleston.data.remote.network.HttpClient
import com.charleston.data.remote.network.UrlProvider
import com.charleston.data.remote.request.MarvelApi
import com.charleston.data.repository.MarvelRepository
import com.charleston.domain.repository.IMarvelRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.util.*

object DataModule {

    private val module = module {
        single {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
        }
        single { UrlProvider() }
        single { get<HttpClient>().create(MarvelApi::class.java) }
        single { MarvelCloud(get()) }
        single { MarvelLocal(get()) }
        single<IMarvelRepository> { MarvelRepository(get(), get()) }
    }

    fun loadModule() {
        loadKoinModules(module)
    }

    fun loadNetworkModule(urlProvider: UrlProvider? = null) {
        loadKoinModules(
            module { single { HttpClient(androidApplication(), urlProvider ?: get()) } }
        )
    }
}