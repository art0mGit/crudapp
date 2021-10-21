package com.kotlinspringpostgresql.crudapp


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
	exclude = [DataSourceAutoConfiguration::class], scanBasePackageClasses = [CrudApplication::class]
)
@ConfigurationPropertiesScan
class CrudApplication

fun main(args: Array<String>) {
	runApplication<CrudApplication>(*args)
}
