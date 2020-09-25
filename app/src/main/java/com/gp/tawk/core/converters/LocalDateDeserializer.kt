package com.gp.tawk.core.converters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.gp.tawk.core.helpers.DateHelper
import org.joda.time.LocalDateTime
import java.lang.reflect.Type

class LocalDateDeserializer : JsonDeserializer<LocalDateTime?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return DateHelper.toLocalDateTime(json?.asString!!)
    }
}