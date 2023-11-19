package pl.edu.mimuw.blitz.strategies

import pl.edu.mimuw.blitz.PlayerGameState

fun interface Strategy {
    fun shouldReRoll(
        gameState: PlayerGameState
    ): Boolean
}