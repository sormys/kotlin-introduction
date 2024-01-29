package pl.edu.mimuw.game

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import pl.edu.mimuw.player.Player
import kotlin.test.assertEquals

class GameStateTest {
    private val boards = listOf(
        Board.BoardSize.SMALL,
        Board.BoardSize.MEDIUM,
        Board.BoardSize.LARGE
    )

    @TestFactory
    fun testNextMove() = boards.flatMap { boardSize ->
        listOf(
            SimpleState(Seega.Stage.PLACING, Player.Color.WHITE, 0, 0, false) to Player.Color.BLACK,
            SimpleState(Seega.Stage.PLACING, Player.Color.WHITE, 1, 0, false) to Player.Color.BLACK,
            SimpleState(Seega.Stage.PLACING, Player.Color.BLACK, 0, 0, false) to Player.Color.WHITE,
            SimpleState(Seega.Stage.MOVING, Player.Color.WHITE, 50, 0, true) to Player.Color.WHITE,
            SimpleState(Seega.Stage.MOVING, Player.Color.WHITE, 50, 0, false) to Player.Color.BLACK,
            SimpleState(Seega.Stage.MOVING, Player.Color.BLACK, 50, 0, true) to Player.Color.BLACK,
            SimpleState(Seega.Stage.MOVING, Player.Color.BLACK, 50, 0, false) to Player.Color.WHITE,
        ).map { (simpleState, expectedPlayer) ->
            DynamicTest.dynamicTest("testPrepareGame($boardSize, $simpleState, $expectedPlayer)") {
                val board = Board(boardSize)
                val gameState = GameState(
                    board,
                    simpleState.stage,
                    simpleState.currentPlayer,
                    simpleState.moves,
                    simpleState.noCapture
                )
                val deployWhite = Position(0, 0)
                val deployBlack = Position(1, 0)

                board.setField(deployWhite, Board.Field.WHITE)
                board.setField(deployBlack, Board.Field.BLACK)
                gameState.nextMove(simpleState.capture)

                assertEquals(
                    gameState.currentPlayer, expectedPlayer
                )
            }
        }
    }

    @TestFactory
    fun testNextStage() = boards.flatMap { boardSize ->
        listOf(
            SimpleState(Seega.Stage.PLACING, Player.Color.WHITE, 0, 0, false) to Seega.Stage.PLACING,
            SimpleState(Seega.Stage.PLACING, Player.Color.BLACK, 0, 0, false) to Seega.Stage.PLACING,
            SimpleState(
                Seega.Stage.PLACING, Player.Color.WHITE, boardSize.dimension() * boardSize.dimension() - 2,
                0, true
            ) to Seega.Stage.MOVING,
            SimpleState(
                Seega.Stage.PLACING, Player.Color.BLACK, boardSize.dimension() * boardSize.dimension() - 2,
                0, true
            ) to Seega.Stage.MOVING,
        ).map { (simpleState, expectedStage) ->
            DynamicTest.dynamicTest("testPrepareGame($boardSize, $simpleState, $expectedStage)") {
                val board = Board(boardSize)
                val gameState = GameState(
                    board,
                    simpleState.stage,
                    simpleState.currentPlayer,
                    simpleState.moves,
                    simpleState.noCapture
                )
                val deployWhite = Position(0, 0)
                val deployBlack = Position(1, 0)

                board.setField(deployWhite, Board.Field.WHITE)
                board.setField(deployBlack, Board.Field.BLACK)
                gameState.nextMove(simpleState.capture)

                assertEquals(gameState.stage, expectedStage)
            }
        }
    }

    @TestFactory
    fun testIsFinished() = boards.flatMap { boardSize ->
        listOf(
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.WHITE)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 0
            ) to true,
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.BLACK)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 0
            ) to false,
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.BLACK)
                    setField(Position(2, 0), Board.Field.BLACK)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 20
            ) to true,
            GameState(boardSize) to false
        ).map { (gameState, expectedResult) ->
            DynamicTest.dynamicTest("testIsFinished($boardSize, $gameState)") {
                assertEquals(expectedResult, gameState.isFinished())
            }
        }
    }

    @TestFactory
    fun testWinner() = boards.flatMap { boardSize ->
        listOf(
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.WHITE)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 0
            ) to Player.Color.WHITE,
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.BLACK)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 0
            ) to null,
            GameState(
                board = Board(boardSize).apply {
                    setField(Position(0, 0), Board.Field.WHITE)
                    setField(Position(1, 0), Board.Field.BLACK)
                    setField(Position(2, 0), Board.Field.BLACK)
                },
                stage = Seega.Stage.MOVING,
                currentPlayer = Player.Color.WHITE,
                moves = 50,
                noCapture = 20
            ) to Player.Color.BLACK,
            GameState(boardSize) to null
        ).map { (gameState, expectedResult) ->
            DynamicTest.dynamicTest("testWinner($boardSize, $gameState)") {
                assertEquals(expectedResult, gameState.winner())
            }
        }
    }

    companion object {
        data class SimpleState(
            val stage: Seega.Stage,
            val currentPlayer: Player.Color,
            val moves: Int,
            val noCapture: Int,
            val capture: Boolean,
        )
    }
}