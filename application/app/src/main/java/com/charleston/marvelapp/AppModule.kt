package com.charleston.marvelapp

import com.charleston.marvelapp.screens.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModule {
    private val appModule = module {
        viewModel {
            MainViewModel(get(), get())
        }
    }

    fun loadModule() {
        loadKoinModules(listOf(appModule))
    }
}
