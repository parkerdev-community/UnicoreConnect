package uno.unicore.unicoreconnect.common.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import uno.unicore.unicoreconnect.common.adapters.ISO8601DateParser.format
import uno.unicore.unicoreconnect.common.adapters.ISO8601DateParser.parse
import java.lang.reflect.Type
import java.sql.Timestamp
import java.text.ParseException
import java.util.Date

class ISO8601DateAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, jsonDeserializationContext: JsonDeserializationContext?): Date {
        if (json !is JsonPrimitive) {
            throw JsonParseException("The date should be a string value")
        }
        val date: Date = deserializeToDate(json)
        return if (type === Date::class.java) {
            date
        } else if (type === Timestamp::class.java) {
            Timestamp(date.getTime())
        } else if (type === Date::class.java) {
            Date(date.getTime())
        } else {
            throw IllegalArgumentException("$javaClass cannot deserialize to $type")
        }
    }

    private fun deserializeToDate(json: JsonElement): Date {
        return try {
            parse(json.asString)
        } catch (e: ParseException) {
            throw JsonSyntaxException(json.getAsString(), e)
        }
    }

    override fun serialize(date: Date?, type: Type?, jsonSerializationContext: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(format(date))
    }
}