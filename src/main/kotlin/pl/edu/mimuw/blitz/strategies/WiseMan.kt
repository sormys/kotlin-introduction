package pl.edu.mimuw.blitz.strategies

import pl.edu.mimuw.blitz.Player

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