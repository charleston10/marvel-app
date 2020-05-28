package com.charleston.data

import com.charleston.data.remote.MarvelCloud
import com.charleston.data.remote.network.HttpClient
import com.charleston.data.remote.request.MarvelApi
import com.charleston.data.repository.MarvelRepository
import com.charleston.domain.interactor.ListCharactersUseCase
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
        factory {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
        }
        factory { HttpClient(androidApplication()) }
        factory { get<HttpClient>().create(MarvelApi::class.java) }
        factory { MarvelCloud(get()) }
        factory<IMarvelRepository> { MarvelRepository(get()) }
        factory { ListCharactersUseCase(get()) }
    }

    fun loadModule() {
        loadKoinModules(module)
    }
}