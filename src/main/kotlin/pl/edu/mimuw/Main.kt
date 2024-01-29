package pl.edu.mimuw

import pl.edu.mimuw.board.Board
import pl.edu.mimuw.game.Seega

fun main() {
    val basicStartingBoard: String by lazy {
        val input: StringBuilder = StringBuilder()
        for (i in 0 until Board.BoardSize.SMALL.dimension()) {
            for (j in 0 until Board.BoardSize.SMALL.dimension()) {
                if (i == Board.BoardSize.SMALL.dimension() / 2 && j == Board.BoardSize.SMALL.dimension() / 2)
                    continue
                input.append("deploy ${'a' + i}${j + 1}\n")
            }
        }
        input.toString()
    }
    println(basicStartingBoard)
    Seega.prepareGame(Board.BoardSize.SMALL)
    Seega.play()
    Seega.resetGame()
}
