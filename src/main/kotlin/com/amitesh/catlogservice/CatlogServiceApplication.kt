package com.amitesh.catlogservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatlogServiceApplication

fun main(args: Array<String>) {
	runApplication<CatlogServiceApplication>(*args)
}
