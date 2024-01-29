package pl.edu.mimuw.board

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import pl.edu.mimuw.game.Move
import kotlin.test.Test

class PositionTest {
    @Test
    fun testPositionMoveDirection() {
        val initialPosition = Position(2, 2)

        assertEquals(Position(1, 2), initialPosition.moveDirection(Move.Direction.UP))
        assertEquals(Position(3, 2), initialPosition.moveDirection(Move.Direction.DOWN))
        assertEquals(Position(2, 1), initialPosition.moveDirection(Move.Direction.LEFT))
        assertEquals(Position(2, 3), initialPosition.moveDirection(Move.Direction.RIGHT))
    }

    @Test
    fun testPositionNoneMove() {
        val nonePosition = Position.None
        assertThrows<IllegalArgumentException> {
            nonePosition.moveDirection(Move.Direction.UP)
        }
    }

    @Test
    fun testPositionNeighbours() {
        val position = Position(2, 2)
        val neighbors = listOf(
            Position(1, 2), Position(3, 2), Position(2, 1), Position(2, 3)
        )

        for (neighbor in neighbors) {
            assert(position.isNeighbour(neighbor)) { "Expected $neighbor to be a neighbor of $position" }
        }
    }

    @Test
    fun testPositionFromString() {
        val position = Position(2, 2)
        assertEquals("c3", position.toString())
    }

    @Test
    fun testPositionFromStringInvalid() {
        assertThrows<IllegalArgumentException> {
            Position.fromString("invalid")
        }
    }
}