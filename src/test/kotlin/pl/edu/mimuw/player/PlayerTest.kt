package pl.edu.mimuw.player

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.edu.mimuw.game.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class PlayerTest {

    @AfterEach
    fun tearDown() {
        System.setIn(System.`in`)
        System.setOut(System.out)
    }

    @Test
    fun testPlayerPlay() {
        val inputStream = ByteArrayInputStream("move a1 right".toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        val initialState = GameState(Board(Board.BoardSize.SMALL), Seega.Stage.MOVING, Player.Color.WHITE, 0, 0)
        val player = Player(Player.Color.WHITE)
        val move = player.playMove(initialState)

        assertEquals(Move(Seega.Stage.MOVING, Position(0, 0), Position(0, 1), Player.Color.WHITE), move)
    }

    @Test
    fun testPlayerPlayInvalid() {
        val inputStream = ByteArrayInputStream("invalid input\n\n".toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        val initialState = GameState(Board(Board.BoardSize.SMALL), Seega.Stage.MOVING, Player.Color.WHITE, 0, 0)
        val player = Player(Player.Color.WHITE)

        assertThrows<IllegalArgumentException> {
            player.playMove(initialState)
        }
    }

    @Test
    fun testPlayMoveWhilePlacing() {
        val inputStream = ByteArrayInputStream("move a1 right".toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        val initialState = GameState(Board(Board.BoardSize.SMALL), Seega.Stage.PLACING, Player.Color.WHITE, 0, 0)
        val player = Player(Player.Color.WHITE)

        assertThrows<IllegalArgumentException> {
            player.playMove(initialState)
        }
    }
}