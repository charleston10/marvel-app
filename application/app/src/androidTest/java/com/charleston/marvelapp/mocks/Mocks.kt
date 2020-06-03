package com.charleston.marvelapp.mocks

import android.content.Context
import androidx.annotation.RawRes
import com.charleston.marvelapp.R
import org.apache.commons.io.IOUtils

fun mockEventsJson(context: Context): String {
    return context getJson R.raw.mock_events
}

private infix fun Context.getJson(@RawRes raw: Int): String {
    val inputStream = resources.openRawResource(raw)
    val json = IOUtils.toString(inputStream)
    IOUtils.closeQuietly(inputStream)
    return json
}