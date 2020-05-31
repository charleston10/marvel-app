package com.charleston.data.mappers

import com.charleston.data.extensions.getImageUrl
import com.charleston.data.remote.response.EventResponse
import com.charleston.domain.model.ItemModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class EventMapper : IMapper<EventResponse, ItemModel> {

    override fun transform(entity: EventResponse): ItemModel {
        return ItemModel(
            name = entity.title,
            description = updateDescription(entity.startDate, entity.endDate, entity.description),
            imageUrl = entity.thumbnail.getImageUrl()
        )
    }

    private fun updateDescription(startDate: Date, endDate: Date, description: String?): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM", Locale("pt", "BR"))
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }

        return "The event occurs ${dateFormat.format(startDate)} to ${dateFormat.format(endDate)}\n$description"
    }
}