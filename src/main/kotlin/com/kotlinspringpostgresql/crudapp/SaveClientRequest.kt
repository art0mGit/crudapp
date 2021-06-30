package com.kotlinspringpostgresql.crudapp

import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class SaveClientRequest (

    @get:NotNull
    @get:Size(min = 1, max = 50)
    val name: String?,

    @get:NotNull
    @get:Size(min = 1, max = 50)
    val email: String?,

    @get:NotNull
    @get:Size(min = 1, max = 50)
    val phone: String?,

    @get:NotNull
    @get:Past
    val created: LocalDateTime?
)