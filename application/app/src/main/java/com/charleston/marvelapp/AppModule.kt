package com.charleston.marvelapp

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModule {
    private val appModule = module {
        viewModel { MarvelViewModel(get()) }
    }

    fun loadModule() {
        loadKoinModules(listOf(appModule))
    }
}
