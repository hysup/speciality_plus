package com.example.kotlin25

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class Kotlin25SpringApplication
fun main(args: Array<String>) {
    runApplication<Kotlin25SpringApplication>(*args)
}
