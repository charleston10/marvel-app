package com.charleston.domain

import com.charleston.domain.interactor.ListUseCase
import com.charleston.domain.interactor.ListThemesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    private val domain = module {
        factory { ListUseCase(get()) }
        factory { ListThemesUseCase(get()) }
    }

    fun loadModule() {
        loadKoinModules(domain)
    }
}