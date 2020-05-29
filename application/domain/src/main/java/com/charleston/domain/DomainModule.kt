package com.charleston.domain

import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.interactor.ListThemesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    private val domain = module {
        factory { ListCharactersUseCase(get()) }
        factory { ListThemesUseCase(get()) }
    }

    fun loadModule() {
        loadKoinModules(domain)
    }
}