package com.charleston.data.mappers

import com.charleston.data.extensions.getImageUrl
import com.charleston.data.remote.response.EventResponse
import com.charleston.domain.model.ItemModel

class EventMapper : IMapper<EventResponse, ItemModel> {

    override fun transform(entity: EventResponse): ItemModel {
        return ItemModel(
            name = entity.title,
            description = entity.description,
            imageUrl = entity.thumbnail.getImageUrl()
        )
    }
}