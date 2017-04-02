package io.github.zebrateam

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import org.dbunit.database.DatabaseConfig
import org.dbunit.database.DatabaseDataSourceConnection
import org.dbunit.database.IDatabaseConnection
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import javax.persistence.EntityManager
import javax.sql.DataSource


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@TestExecutionListeners(
        DependencyInjectionTestExecutionListener::class,
        DirtiesContextTestExecutionListener::class,
        TransactionDbUnitTestExecutionListener::class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(loader = SpringBootContextLoader::class, classes = arrayOf(ZebraApplication::class))
annotation class IntegrationTest


@Component
class FlywayCleanStrategy {

    @Bean
    fun cleanMigrateStrategy() = FlywayMigrationStrategy {
        it.clean()
        it.migrate()
    }
}
