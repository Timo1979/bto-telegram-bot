package by.tut.efreet.telegram.bot

import org.springframework.stereotype.Component

@Component
class Logic {
    fun splitFirstWord(input:String): String {
        val a = input.split("\\s+".toRegex())
        return a.subList(1, a.size).joinToString(" ")
    }
}
