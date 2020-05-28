package com.charleston.domain.repository

import com.charleston.domain.model.CharacterModel

interface IMarvelRepository {
    suspend fun getCharacters(page: Int, limit: Int): List<CharacterModel>
}