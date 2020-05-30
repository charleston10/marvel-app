package com.charleston.domain.interactor

import com.charleston.domain.model.ItemModel
import com.charleston.domain.repository.IMarvelRepository

class ListCharactersUseCase constructor(
    private val repository: IMarvelRepository
) {

    suspend fun execute(): List<ItemModel> {
        return repository.getCharacters(offset = 1, limit = 20)
    }
}