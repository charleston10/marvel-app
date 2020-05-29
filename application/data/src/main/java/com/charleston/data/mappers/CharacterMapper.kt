package com.charleston.data.mappers

import com.charleston.data.remote.response.CharacterResponse
import com.charleston.domain.model.CharacterModel

class CharacterMapper : IMapper<CharacterResponse, CharacterModel> {

    override fun transform(entity: CharacterResponse): CharacterModel {
        return CharacterModel(
            name = entity.name,
            description = entity.description,
            imageUrl = getImageURI(entity.thumbnail.path, entity.thumbnail.extension)
        )
    }

    private fun getImageURI(path: String, extension: String): String {
        return if(isNoImage(path)){
            "http://cdn30.us1.fansshare.com/image/marvel/marvel-logo-hd-images-wallpapers-logo-838491383.jpg"
        }else{
            path.plus(".").plus(extension)
        }

    }

    private fun isNoImage(path: String): Boolean {
        return path.split("/").find { it.contains("image_not_available") }?.isNotEmpty() ?: false
    }
}