package com.kotlinspringpostgresql.crudapp.dao
import com.kotlinspringpostgresql.crudapp.models.Clients
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ClientDao : CrudRepository<Clients, Int> {

    fun findByOrderByName(): List<Clients>
}
