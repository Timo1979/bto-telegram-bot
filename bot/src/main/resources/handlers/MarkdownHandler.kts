import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage

handler("/md") {

    process { _, _ ->
        MarkdownMessage("Привет, *пользователь*!")
    }

}
