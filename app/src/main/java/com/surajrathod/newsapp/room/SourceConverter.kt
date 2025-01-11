package com.surajrathod.newsapp.room

import androidx.room.TypeConverter
import com.surajrathod.newsapp.data.Source
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceConverter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toSource(json: String?): Source? {
        return json?.let { Json.decodeFromString(it) }
    }
}