package by.tut.efreet.telegram.bot.helpers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class JsonDateSerializer : JsonSerializer<Date?>() {
    @Throws(IOException::class)
    override fun serialize(date: Date?, gen: JsonGenerator, provider: SerializerProvider) {
        val dateFormat: SimpleDateFormat = formatDate4Json_Pool.borrowObject()
        try {
            val formattedDate = dateFormat.format(date)
            gen.writeString(formattedDate)
        } finally {
            formatDate4Json_Pool.returnObject(dateFormat)
        }
    }
}
