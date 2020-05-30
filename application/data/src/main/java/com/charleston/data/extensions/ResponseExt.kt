package com.charleston.data.extensions

import com.charleston.data.remote.response.ThumbnailResponse

fun ThumbnailResponse.getImageUrl(): String {
    return getImageURI(path, extension)
}

private fun getImageURI(path: String, extension: String): String {
    return if (isNoImage(path)) {
        "http://cdn30.us1.fansshare.com/image/marvel/marvel-logo-hd-images-wallpapers-logo-838491383.jpg"
    } else {
        path.plus(".").plus(extension)
    }

}

private fun isNoImage(path: String): Boolean {
    return path.split("/").find { it.contains("image_not_available") }?.isNotEmpty() ?: false
}