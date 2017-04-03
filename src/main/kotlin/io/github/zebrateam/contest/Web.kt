package io.github.zebrateam.contest

import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import javax.validation.Valid

@RestController
@RequestMapping("/contest/")
class ContestController(
    private val contestService: ContestService
) {

    @GetMapping
    fun getAll(): List<ContestDto> {
        return contestService.getContests()
    }

    @GetMapping("\$id/")
    fun get(@RequestParam("id") id: Int): ContestDto {
        return contestService.getContest(id) ?: throw IllegalArgumentException("Do not exist")
    }

    @PutMapping
    fun save(@Valid @RequestBody contestDto: ContestDto): ContestDto {
        return contestService.save(contestDto)
    }

    @PostMapping
    fun update(@Valid @RequestBody contestDto: ContestDto): ContestDto {
        return contestService.update(contestDto)
    }

    @DeleteMapping("\$id/")
    fun delete(@RequestParam("id") id: Int) {
        contestService.delete(id)
    }

}
