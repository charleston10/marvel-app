package com.charleston.domain.interactor

import com.charleston.domain.model.ThemeModel
import com.charleston.domain.repository.IMarvelRepository

class ListThemesUseCase constructor(
    private val repository: IMarvelRepository
) {
    suspend fun execute(): List<ThemeModel> {
        return repository.getThemes()
    }
}