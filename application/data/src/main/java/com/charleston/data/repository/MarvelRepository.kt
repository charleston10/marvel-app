package com.charleston.data.repository

import com.charleston.data.mappers.CharacterMapper
import com.charleston.data.remote.MarvelCloud
import com.charleston.domain.model.CharacterModel
import com.charleston.domain.repository.IMarvelRepository

class MarvelRepository(
    private val cloud: MarvelCloud
) : IMarvelRepository {

    private val mapper = CharacterMapper()

    override suspend fun getCharacters(page: Int, limit: Int): List<CharacterModel> {
        val list = cloud.getCharacters(page, limit).data.characters
        return mapper.transform(list)
    }
}