package com.charleston.domain.model

import java.io.Serializable

data class ItemModel(
    val name: String,
    val description: String?,
    val imageUrl: String
): Serializable