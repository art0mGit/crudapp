package com.kotlinspringpostgresql.crudapp

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ClientNotFoundException(id: Int): RuntimeException("Band with id=$id not found")
