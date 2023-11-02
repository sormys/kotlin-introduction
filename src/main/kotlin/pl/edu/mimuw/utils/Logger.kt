package pl.edu.mimuw.utils

class Logger(private val name: String) {
    fun log(content: String) {
        println("$name: $content")
    }
}