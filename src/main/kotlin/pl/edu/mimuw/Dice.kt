package pl.edu.mimuw
import pl.edu.mimuw.utils.Logger
import kotlin.random.Random

class Dice(val sides: Int) {

    private var logger: Logger = Logger("Dice($sides-sided)")
    init {
        require(sides > 0) {"How should dice with $sides sides look like?"}
    }

    fun roll(): Int {
        val roll: Int = Random.nextInt(1, sides+1)
        logger.log("rolled $roll.")
        return roll
    }
}