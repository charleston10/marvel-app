package com.charleston.domain.interactor

import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeEnum
import com.charleston.domain.model.ThemeModel
import com.charleston.domain.repository.IMarvelRepository

class ListUseCase constructor(
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
            ThemeEnum.SERIES -> getCharacters(page, perPage, queryName)
            ThemeEnum.COMICS -> getCharacters(page, perPage, queryName)
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
}