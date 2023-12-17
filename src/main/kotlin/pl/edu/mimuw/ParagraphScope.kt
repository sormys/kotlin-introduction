package pl.edu.mimuw

import pl.edu.mimuw.textHighlight.BoldText
import pl.edu.mimuw.textHighlight.ItalicText

@SMDDsl
class ParagraphScope(){

    private var content: String = ""
    val paragraph: String
        get() = "$content\n\n"

    operator fun String.unaryPlus(){
        content += this.replace("\n+".toRegex(), "  \n")
    }

    fun header(level: Int, action: HeaderScope.() -> Unit){
        content += HeaderScope(level).apply(action).header
    }

    fun bold(action: BoldText.() -> Unit) {
        content += BoldText().apply(action).text
    }

    fun italic(action: ItalicText.() -> Unit) {
        content += ItalicText().apply(action).text
    }
}