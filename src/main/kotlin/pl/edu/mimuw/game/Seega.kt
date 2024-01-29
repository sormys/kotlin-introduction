package pl.edu.mimuw.game

import pl.edu.mimuw.player.Player

object Seega {

    enum class Stage {
        PLACING,
        MOVING,
    }

    private var state: GameState = GameState.empty
    private val players: Map<Player.Color, Player> = mapOf(
        Player.Color.WHITE to Player(Player.Color.WHITE),
        Player.Color.BLACK to Player(Player.Color.BLACK)
    )

    fun prepareGame(boardSize: Board.BoardSize) {
        state = GameState(boardSize)
    }

    fun play() {
        check(!GameState.isEmpty(state)) { "Game not prepared! Please call prepareGame() first" }
        var captured: Boolean
        println(state)
        while (true) {
            try {
                val move: Move = getPlayerMove()
                captured = applyMove(move)
                state.nextMove(captured)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
            println(state)
            if (state.isFinished())
                break
        }
        println("Game finished! Winner: ${state.winner()}")
    }

    private fun applyMove(move: Move): Boolean {
        require(state.board.validateMove(move)) { "Invalid move: $move" }
        var captured = false
        if (move.stage == Stage.PLACING)
            applyPlacingMove(move)
        else
            captured = applyMovingMove(move)
        return captured
    }

    private fun applyPlacingMove(move: Move) =
        state.board.setField(move.to, state.board.mapPlayerToField(move.playerColor))


    private fun applyMovingMove(move: Move): Boolean {
        state.board.setField(move.from, Board.Field.EMPTY)
        state.board.setField(move.to, state.board.mapPlayerToField(move.playerColor))
        return executeCaptures(move.to, move.playerColor)
    }

    private fun executeCaptures(pawnPosition: Position, player: Player.Color): Boolean {
        var captured = false
        for (direction in Move.Direction.entries)
            if (checkCaptureDirection(pawnPosition, direction, player)) {
                capture(pawnPosition.moveDirection(direction))
                captured = true
            }
        return captured
    }

    private fun capture(position: Position) {
        if (state.board.isCentral(position))
            return
        val captured: Player.Color? = state.board.mapFieldToPlayer(state.board.getField(position))
        require(captured != null) { "Cannot capture empty field" }
        state.board.setField(position, Board.Field.EMPTY)
    }

    private fun checkCaptureDirection(position: Position, direction: Move.Direction, player: Player.Color): Boolean {
        val opponentPosition = position.moveDirection(direction)
        val nextPosition = opponentPosition.moveDirection(direction)
        return state.board.checkField(opponentPosition, state.board.mapPlayerToField(player.other()))
                && state.board.checkField(nextPosition, state.board.mapPlayerToField(player))
                && !state.board.isCentral(opponentPosition)
    }

    private fun getPlayerMove(): Move {
        val player = players[state.currentPlayer]
        return player!!.playMove(state)
    }

    fun getGameState(): GameState = state.copy()

    fun resetGame() {
        state = GameState.empty
    }
}