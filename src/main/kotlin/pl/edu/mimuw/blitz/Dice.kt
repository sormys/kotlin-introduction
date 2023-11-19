package pl.edu.mimuw.blitz

import kotlin.random.Random

class Dice(val sides: Int) {


    init {
        require(sides > 0) { "How should dice with $sides sides look like?" }
    }

    fun roll() = Random.nextInt(1, sides + 1)
}