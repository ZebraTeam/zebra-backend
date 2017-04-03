package io.github.zebrateam.contest

import io.github.zebrateam.strip
import org.springframework.stereotype.Component

@Component
class ContestService(
    private val contestRepository: ContestRepository
) {

    fun getContests(): List<ContestDto> {
        return contestRepository.findAll()
            .map { it.toDto() }
    }

    fun getContest(id: Int): ContestDto? = contestRepository.findOne(id).strip()?.toDto()

    fun save(contestDto: ContestDto): ContestDto {
        if (contestDto.id != null) {
            throw IllegalArgumentException("You cannot create contest with specified id")
        }
        return contestRepository.save(contestDto.toEntity()).toDto()
    }


    fun update(contestDto: ContestDto): ContestDto {
        if (contestDto.id == null || contestRepository.exists(contestDto.id)) {
            throw IllegalArgumentException("You cannot update contest when id is null or specified contest do not exists")
        }
        return contestRepository.save(contestDto.toEntity()).toDto()
    }

    fun delete(id: Int) {
        if (!contestRepository.exists(id)) {
            throw IllegalArgumentException("There is no contest with id $id")
        }
        contestRepository.delete(id)
    }

}
