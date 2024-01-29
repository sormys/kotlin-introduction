package pl.edu.mimuw.game

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import pl.edu.mimuw.player.Player

class BoardTest {

    var boards = listOf(
        Board.BoardSize.SMALL,
        Board.BoardSize.MEDIUM,
        Board.BoardSize.LARGE
    )

    @TestFactory
    fun testSetAndGetField() = boards.flatMap { boardSize ->
        listOf(
            Position(0, 0) to Board.Field.WHITE,
            Position(1, 1) to Board.Field.BLACK,
            Position(2, 2) to Board.Field.EMPTY
        ).map { (position, field) ->
            DynamicTest.dynamicTest("testSetAndGetField($boardSize, $position, $field)") {
                val board = Board(boardSize)
                board.setField(position, field)
                assertEquals(field, board.getField(position))
            }
        }
    }

    @TestFactory
    fun testValidateMove() = boards.flatMap { boardSize ->
        listOf(
            Move(Seega.Stage.MOVING, Position(0, 0), Position(0, 1), Player.Color.WHITE) to true,
            Move(Seega.Stage.MOVING, Position(1, 1), Position(1, 1), Player.Color.BLACK) to false,
            Move(Seega.Stage.MOVING, Position(1, 1), Position(1, 2), Player.Color.BLACK) to true,
            Move(Seega.Stage.MOVING, Position(1, 1), Position(1, 3), Player.Color.BLACK) to false,
            Move(Seega.Stage.MOVING, Position(1, 1), Position(2, 1), Player.Color.BLACK) to true,
        ).map { (move, expected) ->
            DynamicTest.dynamicTest("testValidateMove($boardSize, $move, $expected)") {
                val deployWhite = Position(0, 0)
                val deployBlack = Position(1, 1)

                val board = Board(boardSize)
                board.setField(deployWhite, Board.Field.WHITE)
                board.setField(deployBlack, Board.Field.BLACK)

                assertEquals(expected, board.validateMove(move))
            }
        }

    }

    @TestFactory
    fun testCheckRange() = boards.flatMap { boardSize ->
        val max = boardSize.dimension() - 1

        listOf(
            Position(0, 0) to true,
            Position(max, max) to true,
            Position(-1, 0) to false,
            Position(0, max + 1) to false,
            Position(max + 1, 0) to false,
        ).map { (position, expected) ->
            DynamicTest.dynamicTest("testCheckRange($boardSize, $position, $expected)") {
                val board = Board(boardSize)
                assertEquals(expected, board.checkRange(position))
            }
        }
    }

    @TestFactory
    fun testPawnsCount() = boards.map { boardSize ->
        DynamicTest.dynamicTest("testPawnsCount($boardSize)") {
            val board = Board(boardSize)

            // Set fields
            board.setField(Position(0, 0), Board.Field.WHITE)
            board.setField(Position(1, 1), Board.Field.WHITE)
            board.setField(Position(2, 2), Board.Field.BLACK)

            // Check pawn count
            assertEquals(2, board.pawnsCount(Player.Color.WHITE))
            assertEquals(1, board.pawnsCount(Player.Color.BLACK))
        }
    }
}