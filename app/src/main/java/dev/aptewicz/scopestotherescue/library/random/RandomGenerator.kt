package dev.aptewicz.scopestotherescue.library.random

import kotlin.random.Random

interface RandomGenerator {
    fun nextInt(): Int
}

class RandomGeneratorImpl : RandomGenerator {
    override fun nextInt(): Int = Random.nextInt(100)
}
