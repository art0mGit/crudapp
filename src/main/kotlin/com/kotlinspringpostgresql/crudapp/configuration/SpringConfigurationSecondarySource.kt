package com.kotlinspringpostgresql.crudapp.configuration

import com.kotlinspringpostgresql.crudapp.dao.secondary.ClientSecondarySource
import com.kotlinspringpostgresql.crudapp.models.Clients
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
    basePackageClasses = [ClientSecondarySource::class],
    entityManagerFactoryRef = "secondaryManagerFactory",
    transactionManagerRef = "secondaryTransactionManagerFactory"
)

class SpringConfigurationSecondarySource {

    @Configuration
    @ConfigurationProperties(prefix = "spring.second-datasource")
    class JdbcSecondaryProperties : HikariConfig()

    @Bean
    @Qualifier("spring.secondDatasource")
    fun secondaryDataSource(properties: JdbcSecondaryProperties) =
        HikariDataSource(properties)

    @Bean
    fun secondaryManagerFactory(
        @Qualifier("spring.secondDatasource")
        datasourcePrim: DataSource,
        pgsqlAdapter: JpaVendorAdapter
    ) =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = datasourcePrim
            jpaVendorAdapter = pgsqlAdapter
            setPackagesToScan(Clients::class.java.`package`.name)
            persistenceUnitName = "secondary"
        }

    @Bean
    fun secondaryTransactionManagerFactory(
        @Qualifier("secondaryManagerFactory") factory : EntityManagerFactory
    ) = JpaTransactionManager(factory)
}

