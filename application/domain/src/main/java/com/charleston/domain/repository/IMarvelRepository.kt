package com.charleston.domain.repository

import com.charleston.domain.model.CharacterModel

interface IMarvelRepository {
    suspend fun getCharacters(offset: Int, limit: Int): List<CharacterModel>
}