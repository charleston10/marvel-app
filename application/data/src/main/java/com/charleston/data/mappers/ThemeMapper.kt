package com.charleston.data.mappers

import com.charleston.data.local.ThemeEntity
import com.charleston.domain.model.ThemeModel

class ThemeMapper : IMapper<ThemeEntity, ThemeModel> {

    override fun transform(entity: ThemeEntity): ThemeModel {
        return ThemeModel(
            id = entity.id,
            name = entity.name,
            imageUrl = entity.imageUrl
        )
    }
}