package pl.edu.mimuw.strategies

import pl.edu.mimuw.Player

object WiseMan : Strategy {
    override fun shouldReRoll(
        playerRole: Player.Role,
        playerRoll: Int,
        opponentRoll: Int,
        numberOfDiceSides: Int,
        playerPoints: Int,
        opponentPoints: Int,
        pointsToWin: Int
    ): Boolean {
        return playerRoll < opponentRoll ||
                playerRole == Player.Role.DEFENCE && playerRoll == opponentRoll
    }
}