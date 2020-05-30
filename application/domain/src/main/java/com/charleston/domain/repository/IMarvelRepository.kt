package com.charleston.domain.repository

import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel

interface IMarvelRepository {
    suspend fun getThemes(): List<ThemeModel>
    suspend fun getCharacters(offset: Int, limit: Int, queryName: String?): List<ItemModel>
}