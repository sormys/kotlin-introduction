package pl.edu.mimuw

class Player(
    private val name: String = "Nobody",
    private var strategy: Strategy,
) {
    enum class Role {
        ATTACK,
        DEFENCE,
        NONE,
    }

    private var logger: Logger = Logger(toString())
    var role = Role.NONE
        set(value) {
            field = value
            logger.log("role changed to $value")
        }

    var points: Int = 0
        private set(value){
            field = value
            logger.log("points changed to $value")
        }

    var roll: Int = 0

    fun willReRoll(
        opponentRoll: Int,
        numberOfDiceSides: Int,
        opponentPoints: Int,
        pointsToWin: Int
    ): Boolean {
        val reRoll = strategy.shouldReRoll(
            playerRole = role,
            playerRoll = roll,
            opponentRoll = opponentRoll,
            numberOfDiceSides = numberOfDiceSides,
            playerPoints = points,
            opponentPoints = opponentPoints,
            pointsToWin = pointsToWin
        )
        if (reRoll)
            logger.log("will reroll")
        else
            logger.log("won't reroll")
        return reRoll
    }

    fun newGame(){
        role = Role.NONE
        points = 0
        roll = 0
    }

    fun endRound(isWinner: Boolean){
        role = when (role) {
            Role.ATTACK -> Role.DEFENCE
            Role.DEFENCE -> Role.ATTACK
            Role.NONE -> {
                logger.log("role is NONE, skipping")
                return
            }
        }

        if (isWinner)
            points++
    }

    override fun toString(): String {
        return "Player $name"
    }
}