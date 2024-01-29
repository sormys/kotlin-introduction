package pl.edu.mimuw.game

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import pl.edu.mimuw.board.Board
import pl.edu.mimuw.player.Player
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SeegaTest {
    private val boards = listOf(
        Board.BoardSize.SMALL,
        Board.BoardSize.MEDIUM,
        Board.BoardSize.LARGE
    )

    @AfterEach
    fun teardown() {
        Seega.resetGame()
    }

    @TestFactory
    fun testPrepareGame() = boards.map { boardSize ->
        dynamicTest("testPrepareGame($boardSize)") {

            Seega.prepareGame(boardSize)
            val result: GameState = Seega.getGameState()

            assertEquals(boardSize, result.board.size)
            assertEquals(Player.Color.WHITE, result.currentPlayer)
            assertEquals(result.moves, 0)
            assertEquals(result.noCapture, 0)
            assertEquals(result.stage, Seega.Stage.PLACING)
        }
    }

    @TestFactory
    fun testResetGame() = boards.map { boardSize ->
        dynamicTest("testResetGame($boardSize)") {

            Seega.prepareGame(boardSize)
            Seega.resetGame()
            val result: GameState = Seega.getGameState()

            assertTrue(GameState.isEmpty(result))
            assertThrows<IllegalStateException> { Seega.play() }
        }
    }

    @TestFactory
    fun testPlayWithoutPrepare() = boards.map { boardSize ->
        dynamicTest("testPlayWithoutPrepare($boardSize)") {
            assertThrows<IllegalStateException> { Seega.play() }
        }
    }
}