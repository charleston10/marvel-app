package com.charleston.domain.model

import java.io.Serializable

data class ThemeModel(
    val id: Int,
    val name: String,
    val imageUrl: String
): Serializable