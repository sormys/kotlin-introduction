package pl.edu.mimuw

class Logger(private val name: String) {
    fun log(content: String){
        println("$name: $content")
    }
}