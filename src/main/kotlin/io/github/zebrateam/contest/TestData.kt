package io.github.zebrateam.contest

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ContestTestData(
    private val contestRepository: ContestRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        contestRepository.save(listOf(
            TeamContest("PTWPZ", LocalDateTime.now(), LocalDateTime.now().plusDays(1), null, penalty = 20),
            IndividualContest("TEST", LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, printing = true)
        ))
    }
}
