import by.tut.efreet.telegram.bot.Logic
import by.tut.efreet.telegram.bot.NameGenerator
import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage

handler("/start") {

    val logicBean = getBean<Logic>()
    process { state, _ ->
//        val a = state.initialMessage.split("\\s+".toRegex())
//
//        MarkdownMessage("Привет, ${a.subList(1, a.size).joinToString(" ")}!")
        MarkdownMessage("Привет, ${logicBean.splitFirstWord(state.initialMessage)}!")
    }

}
