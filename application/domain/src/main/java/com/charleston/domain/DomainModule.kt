package com.charleston.domain

import com.charleston.domain.interactor.ListEventUseCase
import com.charleston.domain.interactor.ListItemUseCase
import com.charleston.domain.interactor.ListThemesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    private val domain = module {
        factory { ListItemUseCase(get()) }
        factory { ListThemesUseCase(get()) }
        factory { ListEventUseCase(get()) }
    }

    fun loadModule() {
        loadKoinModules(domain)
    }
}