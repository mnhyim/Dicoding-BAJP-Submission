package com.mnhyim.moviecatalog.utils

import androidx.room.TypeConverter

class DataConverters {

    @TypeConverter
    fun fromString(string: String): List<String> {
        return string.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromInt(string: String): List<Int> {
        return string.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun toInt(stringList: List<Int>): String {
        return stringList.joinToString(separator = ",")
    }
}