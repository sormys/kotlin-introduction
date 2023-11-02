package pl.edu.mimuw

import pl.edu.mimuw.strategies.Strategy
import pl.edu.mimuw.utils.Logger

class Player(
    private val name: String = "Nobody",
    private var strategy: Strategy,
) {
    enum class Role {
        ATTACK,
        DEFENCE,
        NONE,
    }

    private var logger: Logger = Logger(toString())
    var role = Role.NONE
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
        opponentRoll: Int,
        numberOfDiceSides: Int,
        opponentPoints: Int,
        pointsToWin: Int,
        dice: Dice
    ) {
        if (strategy.shouldReRoll(
                playerRole = role,
                playerRoll = roll,
                opponentRoll = opponentRoll,
                numberOfDiceSides = numberOfDiceSides,
                playerPoints = points,
                opponentPoints = opponentPoints,
                pointsToWin = pointsToWin
            )
        )
            rollDice(dice)
    }

    fun newGame() {
        role = Role.NONE
        points = 0
        roll = 0
    }

    fun rollDice(dice: Dice) {
        logger.log("is rolling")
        roll = dice.roll()
    }

    fun addPoint() {
        points++;
    }

    override fun toString(): String {
        return "Player $name"
    }
}