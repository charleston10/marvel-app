package com.charleston.data.local

import android.content.Context
import com.charleston.data.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.apache.commons.io.IOUtils

class MarvelLocal(
    private val context: Context
) {

    fun getThemes(): List<ThemeEntity> {
        val inputStream = context.resources.openRawResource(R.raw.marvel_themes)
        val json = IOUtils.toString(inputStream)
        IOUtils.closeQuietly(inputStream)

        return  Gson().fromJson(json, object : TypeToken<List<ThemeEntity>>() {}.type)
    }
}