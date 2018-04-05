package com.effeta.miparroquiaandroid.common

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*


/**
 * Created by aulate on 19/3/18.
 */
object DateTimeConverters {
    private val formatter = DateTimeFormat.forPattern(ISO_8601_DATE_FORMAT)

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: String?): DateTime? {
        return value?.let {
            return formatter.parseDateTime(value)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromDateTime(date: DateTime?): String? {
        return date?.toString(formatter)
    }
}