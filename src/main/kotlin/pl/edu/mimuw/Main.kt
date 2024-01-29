package pl.edu.mimuw

import pl.edu.mimuw.game.Board
import pl.edu.mimuw.game.Seega

fun main() {
    Seega.prepareGame(Board.BoardSize.SMALL)
    Seega.play()
    Seega.resetGame()
}
