package com.charleston.data.repository

import com.charleston.data.local.MarvelLocal
import com.charleston.data.mappers.*
import com.charleston.data.remote.MarvelCloud
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.domain.repository.IMarvelRepository

class MarvelRepository(
    private val cloud: MarvelCloud,
    private val local: MarvelLocal
) : IMarvelRepository {

    private val themeMapper = ThemeMapper()
    private val characterMapper = CharacterMapper()
    private val serieMapper = SerieMapper()
    private val comicMapper = ComicMapper()
    private val eventMapper = EventMapper()

    override suspend fun getThemes(): List<ThemeModel> {
        val list = local.getThemes()
        return themeMapper.transform(list)
    }

    override suspend fun getCharacters(
        offset: Int,
        limit: Int,
        queryName: String?
    ): List<ItemModel> {
        val list = cloud.getCharacters(offset, limit, queryName).data.results
        return characterMapper.transform(list)
    }

    override suspend fun getSeries(
        offset: Int,
        limit: Int,
        queryName: String?
    ): List<ItemModel> {
        val list = cloud.getSeries(offset, limit, queryName).data.results
        return serieMapper.transform(list)
    }

    override suspend fun getComics(offset: Int, limit: Int, queryName: String?): List<ItemModel> {
        val list = cloud.getComics(offset, limit, queryName).data.results
        return comicMapper.transform(list)
    }

    override suspend fun getEvents(): List<ItemModel> {
        val list = cloud.getEvents().data.results
        return eventMapper.transform(list)
    }
}