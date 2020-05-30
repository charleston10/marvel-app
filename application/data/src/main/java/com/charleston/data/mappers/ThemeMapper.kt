package com.charleston.data.mappers

import com.charleston.data.local.ThemeEntity
import com.charleston.domain.model.ThemeEnum
import com.charleston.domain.model.ThemeModel

class ThemeMapper : IMapper<ThemeEntity, ThemeModel> {

    override fun transform(entity: ThemeEntity): ThemeModel {
        return ThemeModel(
            type = getType(entity.id),
            name = entity.name,
            imageUrl = entity.imageUrl
        )
    }

    private fun getType(id: Int): ThemeEnum {
        return when (id) {
            1 -> ThemeEnum.CHARACTERS
            2 -> ThemeEnum.SERIES
            3 -> ThemeEnum.COMICS
            else -> ThemeEnum.CHARACTERS
        }
    }
}