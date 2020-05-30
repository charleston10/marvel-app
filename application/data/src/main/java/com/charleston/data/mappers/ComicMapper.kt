package com.charleston.data.mappers

import com.charleston.data.extensions.getImageUrl
import com.charleston.data.remote.response.ComicResponse
import com.charleston.domain.model.ItemModel

class ComicMapper : IMapper<ComicResponse, ItemModel> {

    override fun transform(entity: ComicResponse): ItemModel {
        return ItemModel(
            name = entity.title,
            description = entity.description,
            imageUrl = entity.thumbnail.getImageUrl()
        )
    }
}