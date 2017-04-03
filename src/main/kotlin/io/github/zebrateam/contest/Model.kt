package io.github.zebrateam.contest

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
sealed class AbstractContest(
    open val name: String,
    open val startTime: LocalDateTime,
    open val endTime: LocalDateTime,
    open val freezeTime: LocalDateTime?,
    open val printing: Boolean,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open val id: Int?
)

@Entity
data class IndividualContest(
    override val name: String,
    override val startTime: LocalDateTime,
    override val endTime: LocalDateTime,
    override val freezeTime: LocalDateTime?,
    override val printing: Boolean = false,
    override val id: Int? = null
) : AbstractContest(name, startTime, endTime, freezeTime, printing, id)

@Entity
data class TeamContest(
    override val name: String,
    override val startTime: LocalDateTime,
    override val endTime: LocalDateTime,
    override val freezeTime: LocalDateTime?,
    override val printing: Boolean = false,
    val penalty: Int? = null,
    override val id: Int? = null
) : AbstractContest(name, startTime, endTime, freezeTime, printing, id)
