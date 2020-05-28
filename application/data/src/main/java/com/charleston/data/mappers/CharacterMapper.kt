package com.charleston.data.mappers

import com.charleston.data.remote.response.CharacterResponse
import com.charleston.domain.model.CharacterModel

class CharacterMapper : IMapper<CharacterResponse, CharacterModel> {

    override fun transform(entity: CharacterResponse): CharacterModel {
        return CharacterModel(
            entity.name
        )
    }

    private fun getImageURI(path: String, extension: String): String {
        return path.plus(".").plus(extension)
    }
}