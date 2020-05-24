import by.tut.efreet.telegram.bot.UnpResolverBean
import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.HtmlMessage
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage

handler("/unp") {
    process { state, _ ->
        val bean = getBean<UnpResolverBean>()
        HtmlMessage(bean.resolve(state.initialMessage))
    }
}

