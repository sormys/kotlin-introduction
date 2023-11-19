package pl.edu.mimuw.blitz.strategies

import pl.edu.mimuw.blitz.Player
import pl.edu.mimuw.blitz.PlayerGameState

object WiseMan : Strategy {
    override fun shouldReRoll(
        gameState: PlayerGameState
    ): Boolean =
        gameState.playerRoll < gameState.opponentRoll
                || gameState.playerRole == Player.Role.DEFENCE
                && gameState.playerRoll == gameState.opponentRoll
}