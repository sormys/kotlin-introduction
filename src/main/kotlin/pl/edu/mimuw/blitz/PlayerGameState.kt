package pl.edu.mimuw.blitz

data class PlayerGameState(
    val playerRole: Player.Role,
    val playerRoll: Int,
    val opponentRoll: Int,
    val numberOfDiceSides: Int,
    val playerPoints: Int,
    val opponentPoints: Int,
    val pointsToWin: Int
)
