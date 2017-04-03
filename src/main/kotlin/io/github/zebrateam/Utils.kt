package io.github.zebrateam

import java.util.*

fun <T> Optional<T>.strip() : T? = orElse(null)

