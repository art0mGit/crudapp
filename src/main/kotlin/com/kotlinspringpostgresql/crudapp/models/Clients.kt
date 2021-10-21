package com.kotlinspringpostgresql.crudapp.models

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "clients") // не обязательно
data class Clients(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String?,

   // @Column(name = "email_work") // не обязательно
    val email: String?,

    val phone: String?,

    val created: LocalDateTime?
)