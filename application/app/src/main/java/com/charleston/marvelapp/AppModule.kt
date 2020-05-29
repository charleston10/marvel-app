package com.charleston.marvelapp

import com.charleston.marvelapp.screens.list.ListViewModel
import com.charleston.marvelapp.screens.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModule {
    private val appModule = module {
        viewModel {
            MainViewModel(
                get()
            )
        }
        viewModel { ListViewModel(get()) }
    }

    fun loadModule() {
        loadKoinModules(listOf(appModule))
    }
}
