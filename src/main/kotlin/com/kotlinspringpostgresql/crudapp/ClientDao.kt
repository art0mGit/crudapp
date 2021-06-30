package com.kotlinspringpostgresql.crudapp

import org.springframework.data.repository.CrudRepository

interface ClientDao : CrudRepository<Clients, Int> {

    fun findByOrderByName(): List<Clients>
}
