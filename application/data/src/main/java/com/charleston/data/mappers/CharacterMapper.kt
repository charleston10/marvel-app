package com.charleston.data.mappers

import com.charleston.data.extensions.getImageUrl
import com.charleston.data.remote.response.CharacterResponse
import com.charleston.domain.model.ItemModel

class CharacterMapper : IMapper<CharacterResponse, ItemModel> {

    override fun transform(entity: CharacterResponse): ItemModel {
        return ItemModel(
            name = entity.name,
            description = entity.description,
            imageUrl = entity.thumbnail.getImageUrl()
        )
    }
}