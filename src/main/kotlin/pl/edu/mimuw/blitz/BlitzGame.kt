package pl.edu.mimuw.blitz

import pl.edu.mimuw.utils.Logger

class BlitzGame(
    player1: Player,
    player2: Player,
    private val pointToWin: Int,
) {

    private val logger: Logger = Logger("Game")
    private val statistics: GameStatistics = GameStatistics()
    private var players: Array<Player> = arrayOf(player1, player2)
    private var attackerId: Int = 0
        set(value) {
            field = value
            attacker.role = Player.Role.ATTACK
            defender.role = Player.Role.DEFENCE
        }
    private var gameDice: Dice = Dice(6)
    private val attacker: Player
        get() = players[attackerId]
    private val defender: Player
        get() = players[1 - attackerId]

    init {
        statistics.setup(players)
    }

    private fun getOpponent(player: Player): Player {
        if (player.role == Player.Role.ATTACK) return defender
        return attacker
    }

    private fun switchSides() {
        attackerId = 1 - attackerId
    }

    fun play(rounds: Int, dice: Dice = Dice(6)) {
        gameDice = dice
        for (i in 1..rounds) {
            logger.log("round $i (${statistics.games + 1} total) started!")
            prepareSides(i)
            simulateOneBlitz()
            restartPlayers()
            logger.log("round $i (${statistics.games} total) ended!")
        }
    }

    private fun simulateOneBlitz() {
        var roundEnded = false
        while (!roundEnded) {
            simulateRound()
            roundEnded = summarizeRound()
        }
    }

    private fun simulateRound() {
        firstPart()
        secondPart()
        decideWinner()
        switchSides()
    }

    private fun firstPart() {
        attacker.rollDice(gameDice)
        defender.rollDice(gameDice)
    }

    private fun prepareSides(round: Int) {
        attackerId = 1 - (round) % 2
        attacker.role = Player.Role.ATTACK
        defender.role = Player.Role.DEFENCE
    }

    private fun secondPart() {
        optionalMove(attacker)
        optionalMove(defender)
    }

    private fun decideWinner() {
        if (attacker.roll >= defender.roll) attacker.addPoint()
        else defender.addPoint()
    }

    private fun summarizeRound(): Boolean {
        var endGame = false
        for (player in players) {
            if (player.points >= pointToWin) {
                endGame = true
                statistics.addWin(player)
            }
        }
        return endGame
    }

    private fun optionalMove(player: Player) {
        val opponent: Player = getOpponent(player)
        player.reRoll(
            opponentPoints = opponent.points,
            numberOfDiceSides = gameDice.sides,
            opponentRoll = opponent.roll,
            pointsToWin = pointToWin,
            dice = gameDice
        )
    }

    fun restart() {
        restartPlayers()
        attackerId = 0
        statistics.clear()
        statistics.setup(players)
    }

    private fun restartPlayers() {
        for (player in players) {
            player.newGame()
        }
    }

    fun showStatistics() {
        logger.log(statistics.toString())
    }
}