package by.aleksei.acs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AcsApplication

fun main(args: Array<String>) {
    runApplication<AcsApplication>(*args)
}
