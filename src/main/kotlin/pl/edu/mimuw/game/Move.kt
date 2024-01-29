package pl.edu.mimuw.game

import pl.edu.mimuw.board.Position
import pl.edu.mimuw.player.Player

data class Move(
    val stage: Seega.Stage,
    val from: Position,
    val to: Position,
    val playerColor: Player.Color
) {
    enum class Direction(val direction: String) {
        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right"),
    }

    companion object {
        fun fromString(moveDsc: String, stage: Seega.Stage, playerColor: Player.Color): Move {
            val moveDscWithoutStage: String = moveDsc.split(" ").drop(1).joinToString(" ")
            val positionInput = Position.fromString(moveDscWithoutStage.split(" ")[0])
            val (from, to) = when (stage) {
                Seega.Stage.PLACING -> Pair(Position.None, positionInput)
                Seega.Stage.MOVING -> {
                    Pair(positionInput, (moveDscWithoutStage.split(" ")[1]).let {
                        positionInput.moveDirection(Move.Direction.valueOf(it.uppercase()))
                    })
                }
            }
            return Move(stage, from, to, playerColor)
        }
    }
}