package com.charleston.domain.interactor

import com.charleston.domain.model.CharacterModel
import com.charleston.domain.repository.IMarvelRepository

class ListCharactersUseCase constructor(
    private val repository: IMarvelRepository
) {

    suspend operator fun invoke(): List<CharacterModel> {
        return repository.getCharacters(page = 1, limit = 1)
    }
}