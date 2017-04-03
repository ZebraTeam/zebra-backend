package io.github.zebrateam.contest

import org.springframework.data.jpa.repository.JpaRepository

interface ContestRepository : JpaRepository<AbstractContest, Int>
