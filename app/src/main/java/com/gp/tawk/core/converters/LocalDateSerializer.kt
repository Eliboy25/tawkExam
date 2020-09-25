package com.gp.tawk.core.converters

import com.google.gson.*
import com.gp.tawk.core.helpers.DateHelper
import org.joda.time.LocalDateTime
import java.lang.reflect.Type

class LocalDateSerializer : JsonSerializer<LocalDateTime?> {

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(DateHelper.fromLocalDateTime(src!!))
    }
}