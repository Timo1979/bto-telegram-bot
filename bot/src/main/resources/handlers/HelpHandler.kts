import by.tut.efreet.telegram.bot.Settings
import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.HtmlMessage

handler("/help") {
    process { state, _ ->
        var msg = """
Ваш telegram id: ${state.chat.id}
Подсказка:
<b>/help</b> - получить помощь
<b>/pay</b> (не реализовано) получить заказ для оплаты в ЕРИП услуг Белтехосмотра
<b>/html</b> примеры форматирования html
<b>/md</b> примеры форматирования markdown
<b>/unp &lt;УНП&gt</b> получить инфо о контрагенте по УНП"""
        if (state.chat.id in Settings.privilegedUsers) {
            msg += """
<b>/gai &lt;техпаспорт&gt</b> получить инфо о транспортном средстве от ГАИ (только для избранных)
<b>/erip &lt;№ платежа&gt</b> инфо о платеже ЕРИП"""
        }
        HtmlMessage(msg)
    }
}
