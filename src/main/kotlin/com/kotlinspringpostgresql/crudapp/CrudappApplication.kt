package com.kotlinspringpostgresql.crudapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrudappApplication

fun main(args: Array<String>) {
	runApplication<CrudappApplication>(*args)
}
