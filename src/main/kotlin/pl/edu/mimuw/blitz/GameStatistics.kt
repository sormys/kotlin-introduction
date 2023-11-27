package pl.edu.mimuw.blitz

class GameStatistics {
    private val roleWins: MutableMap<Player.Role, Int> = mutableMapOf()
    private var playerWins: MutableMap<Player, Int> = mutableMapOf()
    var games = 0
        private set
    private val empty: Boolean
        get() = games == 0

    fun setup(players: Iterable<Player>) {
        for (role in Player.Role.entries)
            roleWins[role] = 0
        for (player in players)
            playerWins[player] = 0
    }

    fun addWin(player: Player) {
        if (player.role != null) {
            roleWins[player.role!!] = roleWins.getOrDefault(player.role, 0) + 1
        }
        playerWins[player] = playerWins.getOrDefault(player, 0) + 1
        games++
    }

    private fun statsMapToString(name: String, map: Map<out Any, Int>): String {
        var statistics = "By $name:\n"
        for ((stat, won: Int) in map) {
            val percentOfWins: Float = (won.toFloat() / games.toFloat()) * 100
            statistics += "$stat $percentOfWins% games won \n"
        }
        return statistics
    }

    fun clear() {
        roleWins.clear()
        playerWins.clear()
        games = 0
    }

    override fun toString(): String =
        if (empty)
            "No games were played"
        else
            "Statistics of $games games:\n" +
                    statsMapToString("Roles", roleWins) +
                    statsMapToString("Players", playerWins)

}