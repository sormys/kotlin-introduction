package pl.edu.mimuw

class Game(
    player1: Player,
    player2: Player,
    private val dice: Dice,
    private val pointToWin: Int,
    private val rounds: Int,
    ) {

    private val logger: Logger = Logger("Game")
    private val statistics: GameStatistics = GameStatistics()
    private var players: Array<Player> = arrayOf(player1, player2)
    private var attacker: Int = 0
    private fun opponentId(playerId: Int): Int{
        return 1 - playerId
    }

    init{
        players[attacker].role = Player.Role.ATTACK
        players[opponentId(attacker)].role = Player.Role.DEFENCE
    }

    fun play(){
        for (i in 1..rounds){
            logger.log("round $i started!")
            simulateOneBlitz()
            restartPlayers()
            switchSides(i)
            logger.log("round $i ended!")
        }
    }

    fun restart(){
        attacker = 0
        restartPlayers()
        players[attacker].role = Player.Role.ATTACK
        players[opponentId(attacker)].role = Player.Role.DEFENCE
    }

    fun restartPlayers(){
        for (player in players){
            player.newGame()
        }
    }
    private fun simulateOneBlitz(){
        var roundEnded = false
        while(!roundEnded){
            simulateRound()
            roundEnded = summarizeRound()
        }
    }

    private fun simulateRound(){
        firstPart()
        secondPart()
        decideWinner()
    }

    private fun firstPart(){
        playerMove(attacker)
        playerMove(opponentId(attacker))
    }

    private fun switchSides(round: Int){
        attacker = (round)%2
        players[attacker].role = Player.Role.ATTACK
        players[opponentId(attacker)].role = Player.Role.DEFENCE
    }

    private fun secondPart(){
        optionalMove(attacker)
        optionalMove(opponentId(attacker))
    }

    private fun decideWinner(){
        val attackerWon: Boolean = players[attacker].roll >= players[opponentId(attacker)].roll
        players[attacker].endRound(attackerWon)
        players[opponentId(attacker)].endRound(!attackerWon)
    }

    private fun summarizeRound(): Boolean{
        var endGame = false
        for (player in players){
            if (player.points >= pointToWin){
                endGame = true
                statistics.addWin(player)
            }
        }
        return endGame
    }
    private fun optionalMove(playerId: Int) {
        val opponent: Player = players[opponentId(playerId)]
        if (players[playerId].willReRoll(
            opponentPoints = opponent.points,
            numberOfDiceSides = dice.sides,
            opponentRoll = opponent.roll,
            pointsToWin = pointToWin
        )){
            playerMove(playerId)
        }
    }

    private fun playerMove(playerId: Int) {
        logger.log("${players[playerId]} is about to roll")
        players[playerId].roll = dice.roll()
    }

    fun showStatistics(){
        logger.log(statistics.toString())
    }
}