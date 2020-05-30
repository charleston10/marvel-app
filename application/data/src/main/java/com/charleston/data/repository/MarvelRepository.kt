package com.charleston.data.repository

import com.charleston.data.local.MarvelLocal
import com.charleston.data.mappers.CharacterMapper
import com.charleston.data.mappers.ThemeMapper
import com.charleston.data.remote.MarvelCloud
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.domain.repository.IMarvelRepository

class MarvelRepository(
    private val cloud: MarvelCloud,
    private val local: MarvelLocal
) : IMarvelRepository {

    private val characterMapper = CharacterMapper()
    private val themeMapper = ThemeMapper()

    override suspend fun getCharacters(
        offset: Int,
        limit: Int,
        queryName: String?
    ): List<ItemModel> {
        val list = cloud.getCharacters(offset, limit, queryName).data.characters
        return characterMapper.transform(list)
    }

    override suspend fun getThemes(): List<ThemeModel> {
        val list = local.getThemes()
        return themeMapper.transform(list)
    }
}