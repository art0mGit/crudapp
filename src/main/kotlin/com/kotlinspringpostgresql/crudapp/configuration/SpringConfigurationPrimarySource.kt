package com.kotlinspringpostgresql.crudapp.configuration

import com.kotlinspringpostgresql.crudapp.dao.primary.ClientPrimarySource
import com.kotlinspringpostgresql.crudapp.models.Clients
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackageClasses = [ClientPrimarySource::class],
    entityManagerFactoryRef = "primaryManagerFactory",
    transactionManagerRef = "primaryTransactionManagerFactory"
)
class SpringConfigurationPrimarySource {

    @Configuration
    @ConfigurationProperties(prefix = "spring.datasource")
    class JdbcPrimaryProperties : HikariConfig()

    @Bean
    @Primary
    @Qualifier("spring.datasource")
    fun primaryDataSource(properties: JdbcPrimaryProperties) =
        HikariDataSource(properties)

    @Bean
    @Primary
    fun primaryManagerFactory(
        @Qualifier("spring.datasource")
        datasourcePrim: DataSource,
        pgsqlAdapter: JpaVendorAdapter) =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = datasourcePrim
            jpaVendorAdapter = pgsqlAdapter
            setPackagesToScan(Clients::class.java.`package`.name)
            persistenceUnitName = "primary"
        }

    @Bean
    fun primaryTransactionManagerFactory(
        @Qualifier("primaryManagerFactory") factory : EntityManagerFactory
    ) = JpaTransactionManager(factory)

}