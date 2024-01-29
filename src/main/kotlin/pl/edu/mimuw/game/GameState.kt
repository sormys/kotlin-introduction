package pl.edu.mimuw.game

import pl.edu.mimuw.player.Player

class GameState(boardSize: Board.BoardSize) {

    val board = Board(boardSize)
    var stage = Seega.Stage.PLACING
        private set
    var currentPlayer: Player.Color = Player.Color.WHITE

    private var moves: Int = 0

    private var noCapture: Int = 0

    override fun toString(): String {
        return if (!isFinished())
            "$board Current stage: $stage\n Current player: $currentPlayer"
        else
            "$board\nGame finished! Winner: ${winner()}"
    }

    private fun nextStage() {
        require(stage == Seega.Stage.PLACING) { "Cannot advance to next stage, current stage: $stage" }
        stage = Seega.Stage.MOVING
        noCapture = 0
    }

    private fun switchPlayer() {
        if (stage == Seega.Stage.MOVING && !board.playerHasMove(currentPlayer.other())) {
            if (board.playerHasMove(currentPlayer))
                println("Player ${currentPlayer.other()} has no moves, $currentPlayer continues!")
            else
                println(
                    "Both players have no moves, This was not explained in rules, so we don't care :))\n" +
                            "Game is now corrupted."
                )
        } else {
            currentPlayer = currentPlayer.other()
        }
    }

    fun nextMove(captured: Boolean) {
        moves++
        if (captured) {
            noCapture = 0
        } else {
            noCapture++
            switchPlayer()
        }
        if (stage == Seega.Stage.PLACING && moves == 2 * startingPawns(board.size))
            nextStage()
    }

    fun isFinished(): Boolean =
        stage == Seega.Stage.MOVING
                && (board.pawnsCount(currentPlayer) == 0
                || noCapture >= 20)


    // No description for what happens when players have the same amount of pawns, so We don't care
    fun winner(): Player.Color? {
        if (!isFinished())
            return null
        return if (board.pawnsCount(currentPlayer) > board.pawnsCount(currentPlayer.other()))
            currentPlayer
        else
            currentPlayer.other()
    }

    companion object {
        val empty = GameState(Board.BoardSize.SMALL)

        fun isEmpty(state: GameState): Boolean = state === empty

        fun startingPawns(boardSize: Board.BoardSize): Int =
            when (boardSize) {
                Board.BoardSize.SMALL -> 12
                Board.BoardSize.MEDIUM -> 24
                Board.BoardSize.LARGE -> 40
            }
    }

}