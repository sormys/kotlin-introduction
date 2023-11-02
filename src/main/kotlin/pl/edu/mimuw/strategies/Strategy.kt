package pl.edu.mimuw.strategies

import pl.edu.mimuw.Player

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