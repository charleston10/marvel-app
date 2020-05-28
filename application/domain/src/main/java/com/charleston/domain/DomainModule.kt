package com.charleston.domain

import com.charleston.domain.interactor.ListCharactersUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    private val domain = module {
        factory { ListCharactersUseCase(get()) }
    }

    fun loadModule() {
        loadKoinModules(domain)
    }
}