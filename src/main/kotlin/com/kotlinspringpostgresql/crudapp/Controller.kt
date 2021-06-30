package com.kotlinspringpostgresql.crudapp

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/clients", produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientController(private val clientService: ClientService) {

    @GetMapping
    fun findAll() = clientService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): Clients {
        return clientService.findById(id)
    }

    @PostMapping
    fun create(@Valid @RequestBody request: SaveClientRequest):Clients {
        return clientService.create(request)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Int,
        @Valid @RequestBody request: SaveClientRequest
    ): Clients {
        return clientService.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int
    ): Clients {
        return clientService.delete(id)
    }

}