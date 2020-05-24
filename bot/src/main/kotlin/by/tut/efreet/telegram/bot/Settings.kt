/**
 * для хранения всяких настроек, переданных через переменные окружения
 */
package by.tut.efreet.telegram.bot

object Settings {

    val env = System.getenv()
    val gaiUrl: String = env.getOrDefault("gaiUrl", "http://host.docker.internal:18181/rq")
    val gaiUser: String = env.getOrDefault("gaiUser", "")
    val gaiPass = env.getOrDefault("gaiPass", "")
    val privilegedUsers = env.getOrDefault("privilegedUsers", "").split(",").map { it.toLong() }

    val dbHost = env.getOrDefault("dbHost", "127.0.0.1")
    val dbUser = env.getOrDefault("dbUser", "")
    val dbPass = env.getOrDefault("dbPass", "")
    val dbName = env.getOrDefault("dbName", "")
    val dbPort = env.getOrDefault("dbPort", "3306").toInt()
}
