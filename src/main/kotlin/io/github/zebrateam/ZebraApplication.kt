package io.github.zebrateam

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.transaction.Transactional

@SpringBootApplication
class ZebraApplication

@Component
class StartUp(
        val testRepository: TestRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        testRepository.save(listOf(Test("aaa"), Test("bbb")))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(ZebraApplication::class.java, *args)
}

@Entity
data class Test(
        val name: String,
        @Id @GeneratedValue(strategy = IDENTITY) val id: Int? = null
)

interface TestRepository : JpaRepository<Test, Int>

@RestController
class TestController(
        val entityManager: EntityManager
) {

    @GetMapping(produces = arrayOf("application/stream+json"))
    fun test(): Flux<Test> {
        return JPAQueryFactory(entityManager)
                .selectFrom(QTest.test)
                .fetch().toFlux()
    }
}