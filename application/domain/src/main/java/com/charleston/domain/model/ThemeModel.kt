package com.charleston.domain.model

import java.io.Serializable

data class ThemeModel(
    val type: ThemeEnum,
    val name: String,
    val imageUrl: String
): Serializable