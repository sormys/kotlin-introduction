package pl.edu.mimuw.blitz

import pl.edu.mimuw.utils.Logger

class BlitzGame(
    private var attackPlayer: Player,
    private var defencePlayer: Player,
    private val pointToWin: Int,
) {

    private val logger: Logger = Logger("Game")
    private val statistics: GameStatistics = GameStatistics()
    private var gameDice: Dice = Dice(6)
    private val playersList: List<Player> = listOf(attackPlayer, defencePlayer)
    private val startingPlayer = attackPlayer // needed for switching sides after rounds

    init {
        statistics.setup(playersList)
        attackPlayer.role = Player.Role.ATTACK
        defencePlayer.role = Player.Role.DEFENCE
    }

    private fun opponent(player: Player): Player {
        if (player == attackPlayer) return defencePlayer
        return attackPlayer
    }

    private fun switchSides() {
        attackPlayer = defencePlayer.also { defencePlayer = attackPlayer }
        attackPlayer.role = Player.Role.ATTACK
        defencePlayer.role = Player.Role.DEFENCE
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
        attackPlayer.rollDice(gameDice)
        defencePlayer.rollDice(gameDice)
    }

    private fun prepareSides(round: Int) {
        if ((1 - (round) % 2 != 0 && attackPlayer == startingPlayer)
            || (1 - (round) % 2 == 0 && attackPlayer != startingPlayer)
        ) {
            switchSides()
        } else {
            attackPlayer.role = Player.Role.ATTACK
            defencePlayer.role = Player.Role.DEFENCE
        }

    }

    private fun secondPart() {
        optionalMove(attackPlayer)
        optionalMove(defencePlayer)
    }

    private fun decideWinner() {
        if (attackPlayer.roll >= defencePlayer.roll) attackPlayer.addPoint()
        else defencePlayer.addPoint()
    }

    private fun summarizeRound(): Boolean {
        var endGame = false
        for (player in playersList) {
            if (player.points >= pointToWin) {
                endGame = true
                statistics.addWin(player)
            }
        }
        return endGame
    }

    private fun optionalMove(player: Player) {
        val opponent = opponent(player)
        val gameState = PlayerGameState(
            playerRole = player.role!!,
            playerRoll = player.roll,
            opponentRoll = opponent.roll,
            numberOfDiceSides = gameDice.sides,
            playerPoints = player.points,
            opponentPoints = opponent.points,
            pointsToWin = pointToWin
        )
        player.reRoll(
            gameState,
            dice = gameDice
        )
    }

    fun restart() {
        restartPlayers()
        statistics.clear()
        statistics.setup(playersList)
    }

    private fun restartPlayers() {
        for (player in playersList) {
            player.newGame()
        }
    }

    fun showStatistics() {
        logger.log(statistics.toString())
    }
}