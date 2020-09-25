package com.gp.tawk.core.helpers

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

object DateHelper {

    private val defaultDateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
    private val defaultDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")

    fun toLocalDateTime(date: String) = try {
        LocalDateTime.parse(date, defaultDateTimeFormat)
    } catch (e: Exception) {
        try {
            LocalDateTime.parse(date, defaultDateFormat)
        } catch (e: Exception) {
            null
        }
    }

    fun fromLocalDateTime(localDateTime: LocalDateTime) = try {
        defaultDateTimeFormat.print(localDateTime)
    } catch (e: Exception) {
        try {
            defaultDateFormat.print(localDateTime)
        } catch (e: Exception) {
            null
        }
    }

    fun getLongDate(localDateTime: LocalDateTime, includeDay: Boolean = false) = DateTimeFormat.forPattern(
        if (includeDay) {
            "E, MMMM dd, yyyy - h:mm a"
        } else {
            "MMMM dd, yyyy - h:mm a"
        }
    ).print(localDateTime)
}