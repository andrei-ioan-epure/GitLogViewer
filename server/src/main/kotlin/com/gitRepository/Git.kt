package com.gitRepository

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Git

fun main(args: Array<String>) {
    runApplication<Git>(*args)
}