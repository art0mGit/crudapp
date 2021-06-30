package com.kotlinspringpostgresql.crudapp


import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(private val clientDao: ClientDao) : ClientService {

    override fun findAll(): List<Clients> {
        log.info("Find all clients")
        return clientDao.findByOrderByName()
    }

    override fun findById(id: Int): Clients {
        log.info("Find client with id=$id")
        return clientDao.findByIdOrNull(id) ?: throw ClientNotFoundException(id)
    }

    override fun create(request: SaveClientRequest) : Clients {
        log.info("Create new client with name=${request.name}")
        return clientDao.save(
            Clients(
                name = request.name,
                created = request.created,
                email = request.email,
                phone = request.phone
            )
        )
    }

    override fun update(id: Int, request: SaveClientRequest) : Clients {
        log.info("Update client with id=$id")
        val client = clientDao.findByIdOrNull(id) ?: throw ClientNotFoundException(id)
        return clientDao.save(
            client.copy(
                name = request.name,
                created = request.created
            )
        )
    }

    override fun delete(id: Int) : Clients {
        log.info("Delete client with id=$id")
        val client = clientDao.findByIdOrNull(id) ?: throw ClientNotFoundException(id)
        clientDao.delete(client)
        return client
    }

    companion object {
        private val log = LoggerFactory.getLogger(ClientServiceImpl::class.java)
    }
}