package pl.edu.mimuw

class GameStatistics {
    private val roleWins: MutableMap<Player.Role, Int> = mutableMapOf()
    private var playerWins: MutableMap<Player, Int> = mutableMapOf()
    var games = 0
        private set
    private val empty: Boolean
        get() = games == 0

    fun setup(players: Array<Player>) {
        for (role in Player.Role.entries)
            roleWins[role] = 0
        for (player in players)
            playerWins[player] = 0
    }

    fun addWin(player: Player) {
        roleWins[player.role] = roleWins.getOrDefault(player.role, 0) + 1
        playerWins[player] = playerWins.getOrDefault(player, 0) + 1
        games++
    }

    private fun statsMapToString(name: String, map: Map<out Any, Int>): String {
        var statistics: String = "By $name:\n"
        for ((stat, won: Int) in map) {
            val percentOfWins: Float = (won.toFloat() / games.toFloat()) * 100
            statistics += "$stat $percentOfWins% winned games\n"
        }
        return statistics
    }

    fun clear() {
        roleWins.clear()
        playerWins.clear()
        games = 0
    }

    override fun toString(): String {
        if (empty)
            return "No games were played"
        return "Statistics of $games games:\n" +
                statsMapToString("Roles", roleWins) +
                statsMapToString("Players", playerWins)
    }
}