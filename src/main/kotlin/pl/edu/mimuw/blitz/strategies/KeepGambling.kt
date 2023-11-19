package pl.edu.mimuw.blitz.strategies

import pl.edu.mimuw.blitz.PlayerGameState

object KeepGambling : Strategy {
    override fun shouldReRoll(
        gameState: PlayerGameState
    ): Boolean = true
}