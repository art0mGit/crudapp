package com.kotlinspringpostgresql.crudapp.service

import com.kotlinspringpostgresql.crudapp.models.Clients

interface ClientService {

    fun findAll(): List<Clients>

    fun findById(id: Int): Clients

    fun create(request: SaveClientRequest): Clients

    fun update(id: Int, request: SaveClientRequest): Clients

    fun delete(id: Int): Clients
}