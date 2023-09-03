package com.yargu.studs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudsApplication

fun main(args: Array<String>) {
    runApplication<StudsApplication>(*args)
}
