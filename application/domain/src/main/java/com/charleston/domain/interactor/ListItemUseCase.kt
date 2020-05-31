package com.charleston.domain.interactor

import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeEnum
import com.charleston.domain.model.ThemeModel
import com.charleston.domain.repository.IMarvelRepository

class ListItemUseCase constructor(
    private val repository: IMarvelRepository
) {

    suspend fun execute(
        page: Int,
        perPage: Int,
        queryName: String?,
        themeSelected: ThemeModel
    ): List<ItemModel> {
        return when (themeSelected.type) {
            ThemeEnum.CHARACTERS -> getCharacters(page, perPage, queryName)
            ThemeEnum.SERIES -> getSeries(page, perPage)
            ThemeEnum.COMICS -> getComics(page, perPage)
        }
    }

    private suspend fun getCharacters(
        page: Int,
        perPage: Int,
        queryName: String?
    ): List<ItemModel> {
        return repository.getCharacters(
            offset = page * perPage,
            limit = perPage,
            queryName = queryName
        )
    }

    private suspend fun getSeries(
        page: Int,
        perPage: Int
    ): List<ItemModel> {
        return repository.getSeries(
            offset = page * perPage,
            limit = perPage
        )
    }

    private suspend fun getComics(
        page: Int,
        perPage: Int
    ): List<ItemModel> {
        return repository.getComics(
            offset = page * perPage,
            limit = perPage
        )
    }
}