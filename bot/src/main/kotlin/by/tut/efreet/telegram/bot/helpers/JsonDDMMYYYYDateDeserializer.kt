package by.tut.efreet.telegram.bot.helpers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


class JsonDDMMYYYYDateDeserializer : JsonDeserializer<Date>() {
    @Throws(IOException::class)
    override fun deserialize(jsonparser: JsonParser,
                             deserializationcontext: DeserializationContext): Date {
        val sDate = jsonparser.text
        val pool = formatDDMMYYYY_Pool
        val dateFormat: SimpleDateFormat = pool.borrowObject()
        return try {
            dateFormat.parse(sDate)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        } finally {
            pool.returnObject(dateFormat)
        }
    }
}
