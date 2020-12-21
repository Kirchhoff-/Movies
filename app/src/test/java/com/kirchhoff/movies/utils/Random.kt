package com.kirchhoff.movies.utils

import java.util.UUID
import kotlin.random.Random

@Suppress("unused")
fun Random.nextString(length: Int = 10) = UUID.randomUUID().toString().substring(0, length)
