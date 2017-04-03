package io.github.zebrateam.contest

import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.LocalDateTime

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed class ContestDto(
    open val name: String,
    open val startTime: LocalDateTime,
    open val endTime: LocalDateTime,
    open val freezeTime: LocalDateTime?,
    open val printing: Boolean = false,
    open val id: Int? = null
) {
    abstract fun toEntity(): AbstractContest
}

data class IndividualContestDto(
    override val name: String,
    override val startTime: LocalDateTime,
    override val endTime: LocalDateTime,
    override val freezeTime: LocalDateTime?,
    override val printing: Boolean = false,
    override val id: Int? = null
) : ContestDto(name, startTime, endTime, freezeTime, printing, id) {
    override fun toEntity(): IndividualContest {
        return IndividualContest(name, startTime, endTime, freezeTime, printing, id)
    }
}

data class TeamContestDto(
    override val name: String,
    override val startTime: LocalDateTime,
    override val endTime: LocalDateTime,
    override val freezeTime: LocalDateTime?,
    override val printing: Boolean = false,
    val penalty: Int?,
    override val id: Int? = null
) : ContestDto(name, startTime, endTime, freezeTime, printing) {
    override fun toEntity(): TeamContest {
        return TeamContest(name, startTime, endTime, freezeTime, printing, penalty, id)
    }
}

fun AbstractContest.toDto(): ContestDto {
    return when (this) {
        is IndividualContest -> IndividualContestDto(name, startTime, endTime, freezeTime, printing, id)
        is TeamContest -> TeamContestDto(name, startTime, endTime, freezeTime, printing, id)
    }
}
