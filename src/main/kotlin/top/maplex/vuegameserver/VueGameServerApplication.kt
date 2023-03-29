package top.maplex.vuegameserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VueGameServerApplication

fun main(args: Array<String>) {
    runApplication<VueGameServerApplication>(*args)
}
