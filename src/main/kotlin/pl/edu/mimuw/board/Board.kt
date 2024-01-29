package pl.edu.mimuw.board

import pl.edu.mimuw.game.Move
import pl.edu.mimuw.player.Player

class Board(val size: BoardSize) {
    enum class BoardSize {
        SMALL {
            override fun dimension() = 5
        },
        MEDIUM {
            override fun dimension() = 7
        },
        LARGE {
            override fun dimension() = 9
        };

        abstract fun dimension(): Int
    }

    enum class Field {
        EMPTY {
            override fun toString() = " "
        },
        WHITE {
            override fun toString() = "W"
        },
        BLACK {
            override fun toString() = "B"
        };

        abstract override fun toString(): String
    }

    private class BoardStringBuilder(val board: Board) {
        private val builder = StringBuilder()

        fun appendColumnNames() {
            builder.append("   ")
            for (column in 0 until board.size.dimension())
                builder.append(" ${'a' + column}  ")
            builder.append("\n")
        }

        fun appendRowSeparator() {
            builder.append("  +")
            for (i in 0 until board.size.dimension())
                builder.append("---+")
            builder.append("\n")
        }

        fun appendRow(row: Int) {
            builder.append("${row + 1} |")
            for (column in 0 until board.size.dimension())
                builder.append(" ${board.getField(Position(row, column))} |")
            builder.append("\n")
        }

        fun build(): String {
            appendColumnNames()
            appendRowSeparator()
            for (row in 0 until board.size.dimension()) {
                appendRow(row)
                appendRowSeparator()
            }
            return builder.toString()
        }
    }

    override fun toString(): String = BoardStringBuilder(this).build()

    private val board = Array(size.dimension()) { Array(size.dimension()) { Field.EMPTY } }

    private val pawns = mutableMapOf(
        Player.Color.WHITE to 0,
        Player.Color.BLACK to 0
    )

    fun mapPlayerToField(player: Player.Color): Field =
        when (player) {
            Player.Color.WHITE -> Field.WHITE
            Player.Color.BLACK -> Field.BLACK
        }

    fun mapFieldToPlayer(field: Field): Player.Color? =
        when (field) {
            Field.WHITE -> Player.Color.WHITE
            Field.BLACK -> Player.Color.BLACK
            else -> null
        }

    fun playerHasMove(player: Player.Color): Boolean {
        for (row in 0 until size.dimension())
            for (column in 0 until size.dimension())
                if (getField(Position(row, column)) == mapPlayerToField(player))
                    for (direction in Move.Direction.entries) {
                        val possiblePosition: Position = Position(row, column).moveDirection(direction)
                        if (checkRange(possiblePosition) && getField(possiblePosition) == Field.EMPTY)
                            return true
                    }
        return false
    }

    internal fun setField(position: Position, field: Field) {
        mapFieldToPlayer(board[position.row][position.column])?.let {
            pawns[it] = pawns[it]!! - 1
        }
        board[position.row][position.column] = field
        mapFieldToPlayer(field)?.let {
            pawns[it] = pawns[it]!! + 1
        }
    }

    fun getField(position: Position): Field =
        board[position.row][position.column]

    fun checkField(position: Position, expectedField: Field): Boolean =
        checkRange(position) &&
                board[position.row][position.column] == expectedField

    fun validateMove(move: Move): Boolean =
        (Position.isNone(move.from) && !isCentral(move.to)
                || (checkField(move.from, mapPlayerToField(move.playerColor))
                    && move.from.isNeighbour(move.to)))
        && checkField(move.to, Field.EMPTY)

    fun checkRange(position: Position): Boolean =
        position.isValid(size.dimension())

    fun isCentral(position: Position): Boolean =
        position.row == size.dimension() / 2 && position.column == size.dimension() / 2

    fun pawnsCount(player: Player.Color): Int = pawns[player]!!

}