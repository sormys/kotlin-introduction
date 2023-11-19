package pl.edu.mimuw.blitz

import pl.edu.mimuw.blitz.strategies.Strategy
import pl.edu.mimuw.utils.Logger

class Player(
    private val name: String = "Nobody",
    private var strategy: Strategy,
) {
    enum class Role {
        ATTACK,
        DEFENCE,
    }

    private var logger: Logger = Logger(toString())
    var role: Role? = null
        set(value) {
            field = value
            logger.log("role changed to $value")
        }
    var points: Int = 0
        private set(value) {
            field = value
            logger.log("points changed to $value")
        }
    var roll: Int = 0
        private set

    fun reRoll(
        gameState: PlayerGameState,
        dice: Dice
    ) {
        if (strategy.shouldReRoll(gameState))
            rollDice(dice)
    }

    fun newGame() {
        role = null
        points = 0
        roll = 0
    }

    fun rollDice(dice: Dice) {
        logger.log("is rolling")
        roll = dice.roll()
        logger.log("rolled $roll")
    }

    fun addPoint() {
        points++
    }

    override fun toString(): String {
        return "Player $name"
    }
}