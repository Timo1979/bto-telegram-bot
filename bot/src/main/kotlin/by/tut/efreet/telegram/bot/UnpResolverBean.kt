package by.tut.efreet.telegram.bot

import by.tut.efreet.telegram.bot.helpers.formatDDMMYYYY_Pool
import by.tut.efreet.telegram.bot.helpers.unpInfo
import org.springframework.stereotype.Component
import java.text.DateFormat
import java.util.Date

fun Date?.format(): String? {
    if (this == null) return null
    val sdf = formatDDMMYYYY_Pool.borrowObject()
    val result = sdf.format(this)
    formatDDMMYYYY_Pool.returnObject(sdf)
    return result
}

/**
 * Компонент для запроса информации о контрагенте по УНП
 */
@Component
class UnpResolverBean {
    fun resolve(cmdParams: String): String {
        val split = cmdParams.split("\\s+".toRegex())
        val unp: Int
        try {
            unp = split[1].toInt()
        } catch (e: Exception) {
            return "Передан неправильный УНП"
        }

        val inf = unpInfo(unp) ?: return "Ничего не вернулось"

        return """<b>УНП</b>: ${inf.unp}
<b>Наименование</b>: ${inf.name}
<b>Наименование краткое</b>: ${inf.nameShort}
<b>Адрес</b>:  ${inf.address}
<b>Дата регистрации</b>:  ${inf.regDate.format()}
<b>Состояние</b>:  ${inf.stateDescr}
<b>Причина изменения состояния</b>:  ${inf.changeReason}
<b>Дата изменения состояния</b>:  ${inf.changeDate.format()}"""

    }
}
