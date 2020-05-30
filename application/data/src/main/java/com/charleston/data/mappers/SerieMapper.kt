package com.charleston.data.mappers

import com.charleston.data.extensions.getImageUrl
import com.charleston.data.remote.response.CharacterResponse
import com.charleston.data.remote.response.SerieResponse
import com.charleston.domain.model.ItemModel

class SerieMapper : IMapper<SerieResponse, ItemModel> {

    override fun transform(entity: SerieResponse): ItemModel {
        return ItemModel(
            name = entity.title,
            description = entity.description,
            imageUrl = entity.thumbnail.getImageUrl()
        )
    }
}