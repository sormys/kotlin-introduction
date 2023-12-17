package pl.edu.mimuw

@SMDDsl
class CodeScope(val multiLine: Boolean){

    private val tag: String
        get() = if (multiLine) "```" else "`"
    private var content: String = ""
    val code: String
        get() {
            val sep = if (multiLine) "\n" else ""
            return "$sep$tag$sep${content.replace("^\\s+".toRegex(), "")}$sep$tag$sep"
        }

    operator fun String.unaryPlus(){
        content += this
    }
}