package pl.edu.mimuw.player

import pl.edu.mimuw.game.GameState
import pl.edu.mimuw.game.Move
import pl.edu.mimuw.game.Seega

class Player(private val color: Color) {
    enum class Color(val color: String) {
        WHITE("W"),
        BLACK("B");

        fun other(): Color =
            when (this) {
                WHITE -> BLACK
                BLACK -> WHITE
            }
    }

    private fun verifyInput(input: String, state: GameState) {
        val actionName = when (state.stage) {
            Seega.Stage.PLACING -> "deploy"
            Seega.Stage.MOVING -> "move"
        }
        val boardSize = state.board.size.dimension()
        val requiredInput = "$actionName [a-${'a' + boardSize - 1}][1-${boardSize}]" +
                if (state.stage == Seega.Stage.PLACING) ""
                else " (${Move.Direction.entries.joinToString("|") { it.direction }})"
        val regex = Regex("^$requiredInput$")
        require(regex.matches(input)) { "Invalid input!, expected: $requiredInput\n Current stage: ${state.stage}" }
    }

    private fun getUserInput(state: GameState): String {
        val input: String = readln()
        verifyInput(input, state)
        return input
    }

    fun playMove(state: GameState): Move {
        while (true) {
            try {
                val input = getUserInput(state)
                return Move.fromString(input, state.stage, this.color)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

}