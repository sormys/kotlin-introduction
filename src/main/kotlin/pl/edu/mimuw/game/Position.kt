package pl.edu.mimuw.game

data class Position(
    val row: Int,
    val column: Int
) {

    private fun isNone() = isNone(this)

    fun moveDirection(direction: Move.Direction): Position {
        require(!isNone()) { "Cannot move from None position" }
        return when (direction) {
            Move.Direction.UP -> Position(row - 1, column)
            Move.Direction.DOWN -> Position(row + 1, column)
            Move.Direction.LEFT -> Position(row, column - 1)
            Move.Direction.RIGHT -> Position(row, column + 1)
        }
    }

    override fun toString() = if (isNone()) "None" else "${'a' + row}${column}"

    fun isValid(range: Int): Boolean =
        row in 0 until range && column in 0 until range

    companion object {
        val None = Position(-1, -1)
        fun isNone(position: Position) = position === None

        fun fromString(positionDsc: String): Position {
            val column = positionDsc[0] - 'a'
            val row = positionDsc.substring(1).toIntOrNull()
                ?: throw IllegalArgumentException("Invalid position: $positionDsc")
            return Position(row - 1, column)
        }
    }
}
