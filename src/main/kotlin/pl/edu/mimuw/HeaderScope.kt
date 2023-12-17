package pl.edu.mimuw

@SMDDsl
class HeaderScope(private val level: Int){

    private val tag: String
        get() = if (this.level <= 1) "=" else "-"
    private var content: String = ""
    val header: String
        get() = "${this.content}\n${this.tag.repeat(this.content.length)}\n"


    operator fun String.unaryPlus(){
        content += this.replace("\n", " ")
    }
}