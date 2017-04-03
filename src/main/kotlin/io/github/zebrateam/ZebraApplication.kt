package io.github.zebrateam

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class ZebraApplication

fun main(args: Array<String>) {
    SpringApplication.run(ZebraApplication::class.java, *args)
}
