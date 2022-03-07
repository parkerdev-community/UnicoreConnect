package uno.unicore.unicoreconnect.common.adapters

import com.google.gson.*
import uno.unicore.unicoreconnect.common.adapters.ISO8601DateParser.format
import uno.unicore.unicoreconnect.common.adapters.ISO8601DateParser.parse
import java.lang.reflect.Type
import java.sql.Time
import java.text.ParseException
import java.util.*


class ISO8601TimeAdapter : JsonSerializer<Time>,
    JsonDeserializer<Time> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Time {
        if (json !is JsonPrimitive) {
            throw JsonParseException("The date should be a string value")
        }
        val date = deserializeToDate(json)
        if (type === Time::class.java) {
            return Time(date.time)
        }
        throw IllegalArgumentException("$javaClass cannot deserialize to $type")
    }

    private fun deserializeToDate(json: JsonElement): Date {
        return try {
            parse(json.asString)
        } catch (e: ParseException) {
            throw JsonSyntaxException(json.asString, e)
        }
    }

    override fun serialize(time: Time?, type: Type, jsonSerializationContext: JsonSerializationContext): JsonElement {
        return JsonPrimitive(format(time))
    }
}