package pl.edu.mimuw.blitz.strategies

import pl.edu.mimuw.blitz.Player

interface Strategy {
    fun shouldReRoll(
        playerRole: Player.Role,
        playerRoll: Int,
        opponentRoll: Int,
        numberOfDiceSides: Int,
        playerPoints: Int,
        opponentPoints: Int,
        pointsToWin: Int
    ): Boolean
}