package pl.edu.mimuw

import pl.edu.mimuw.blitz.BlitzGame
import pl.edu.mimuw.blitz.Dice
import pl.edu.mimuw.blitz.Player
import pl.edu.mimuw.blitz.strategies.KeepGambling
import pl.edu.mimuw.blitz.strategies.WiseMan

fun main() {
    val blitzGame = BlitzGame(
        player1 = Player("Jacek", KeepGambling),
        player2 = Player("Franek", WiseMan),
        pointToWin = 4,
    )
    blitzGame.play(10, Dice(4))
    blitzGame.showStatistics()
    blitzGame.play(10, Dice(6))
    blitzGame.showStatistics()
    blitzGame.restart()
    blitzGame.play(2, Dice(10))
    blitzGame.play(2, Dice(16))
    blitzGame.showStatistics()
}
