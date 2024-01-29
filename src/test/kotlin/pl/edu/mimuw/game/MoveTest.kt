package pl.edu.mimuw.game

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import pl.edu.mimuw.board.Position
import pl.edu.mimuw.player.Player
import kotlin.test.Test

class MoveTest {
    @Test
    fun testMoveFromStringDeploy() {
        val moveString = "deploy a1"
        val expectedMove = Move(
            Seega.Stage.PLACING,
            Position.None,
            Position(0, 0),
            Player.Color.WHITE
        )

        val resultMove = Move.fromString(moveString, Seega.Stage.PLACING, Player.Color.WHITE)

        assertEquals(expectedMove, resultMove)
    }

    @Test
    fun testMove() {
        val moveString = "move a1 right"
        val expectedMove = Move(
            Seega.Stage.MOVING,
            Position(0, 0),
            Position(0, 1),
            Player.Color.WHITE
        )

        val resultMove = Move.fromString(moveString, Seega.Stage.MOVING, Player.Color.WHITE)

        assertEquals(expectedMove, resultMove)
    }

    @Test
    fun testMoveInvalidDirection() {
        val moveString = "move a1 invalid"

        assertThrows<IllegalArgumentException> {
            Move.fromString(moveString, Seega.Stage.MOVING, Player.Color.WHITE)
        }
    }
}