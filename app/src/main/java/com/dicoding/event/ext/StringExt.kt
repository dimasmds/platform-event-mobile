package com.dicoding.event.ext

import java.text.SimpleDateFormat
import java.util.*

fun String.formattedDate(oldFormat: String, newFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(oldFormat, Locale.getDefault())
    return try {
        val date = simpleDateFormat.parse(this)
        simpleDateFormat.applyPattern(newFormat)
        if (date != null) {
            simpleDateFormat.format(date)
        } else {
            this
        }
    } catch (e: Exception) {
        this
    }

}