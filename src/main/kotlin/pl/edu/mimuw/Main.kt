package pl.edu.mimuw

fun main() {
    println("Hello Kotlin!")
    Hello().greet()
    val game = Game(
        dice = Dice(6),
        player1 = Player("Jacek", KeepGambling),
        player2 = Player("Franek", KeepGambling),
        pointToWin = 4,
        rounds = 10
        )
    game.play()
    game.showStatistics()
}
