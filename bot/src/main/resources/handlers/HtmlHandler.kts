import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.HtmlMessage

handler("/html") {

    process { _, _ ->
        HtmlMessage(
            """Привет, <b>пользователь!</b>
                    <b>bold</b>, <strong>bold</strong>
                    <i>italic</i>, <em>italic</em>
                    <a href="URL">inline URL</a>
                    <code>inline fixed-width code</code>
                    <pre>pre-formatted fixed-width code block</pre>""")
    }
}
