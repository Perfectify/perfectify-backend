package net.fabilucius.perfectify.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.perfectify")
    fun dataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    fun dataSource(@Value("\${app.datasource.perfectify.maximum-pool-size:5}") maxPoolSize: Int): DataSource =
        (dataSourceProperties().initializeDataSourceBuilder().build() as HikariDataSource)
            .apply {
                maximumPoolSize = maxPoolSize
                poolName = "perfectify-database-pool"
            }

}