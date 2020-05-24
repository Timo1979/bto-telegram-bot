package by.tut.efreet.telegram.bot.helpers

import by.tut.efreet.telegram.bot.model.UnpInfoContainer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import org.slf4j.LoggerFactory
import java.net.URL


val orUnpInfo: ObjectReader = ObjectMapper().readerFor(UnpInfoContainer::class.java)
 private val log = LoggerFactory.getLogger("UnpHelpers")
/**
 * Возвращает информацию об организации по ее УНП (без кэша).
 */
fun unpInfo(unp: Int) =
        try {
            val result = orUnpInfo.readValue<UnpInfoContainer>(URL("http://www.portal.nalog.gov.by/grp/getData?unp=${unp}&type=json"))
            result.row
        } catch (ex: Exception) {
            log.error("error", ex)
            null
        }
