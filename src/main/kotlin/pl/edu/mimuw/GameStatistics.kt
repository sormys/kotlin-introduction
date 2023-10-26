package pl.edu.mimuw

class GameStatistics {
    private val roleWins: MutableMap<Player.Role, Int> = mutableMapOf()
    private var playerWins: MutableMap<Player, Int> = mutableMapOf()
    private var wins = 0

    fun addWin(player: Player){
        roleWins[player.role] = roleWins.getOrDefault(player.role, 0) + 1
        playerWins[player] = playerWins.getOrDefault(player, 0) + 1
        wins++
    }

    private fun playerStatistics(): String {
        var statistics: String = ""
        for ((player: Player, won: Int) in playerWins) {
            val percentOfWins: Float = (won.toFloat() / wins.toFloat()) * 100
            statistics += "$player  $percentOfWins% winned games\n"
        }
        return statistics
    }

    private fun rolesStatistics(): String {
        var statistics: String = ""
        for ((role: Player.Role, won: Int) in roleWins) {
            val percentOfWins: Float = (won.toFloat() / wins.toFloat()) * 100
            statistics += "$role  $percentOfWins% winned games\n"
        }
        return statistics
    }
    override fun toString(): String {
        return "Statistics of $wins games:\n" +
                "By Roles:\n" +
                rolesStatistics() +
                "By Players:\n" +
                playerStatistics()
    }
}