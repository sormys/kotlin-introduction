package pl.edu.mimuw

object KeepGambling : Strategy{
    override fun shouldReRoll(
        playerRole: Player.Role,
        playerRoll: Int,
        opponentRoll: Int,
        numberOfDiceSides: Int,
        playerPoints: Int,
        opponentPoints: Int,
        pointsToWin: Int
    ): Boolean {
        // Allin
        return true
    }
}