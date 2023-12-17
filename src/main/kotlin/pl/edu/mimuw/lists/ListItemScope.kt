package pl.edu.mimuw.lists

import pl.edu.mimuw.textHighlight.BoldText
import pl.edu.mimuw.textHighlight.ItalicText

class ListItemScope {
    var content: String = ""
        private set
    operator fun String.unaryPlus(){
        content += this.replace("\n+".toRegex(), "  \n")
    }
    fun bold(action: BoldText.() -> Unit) {
        content += BoldText().apply(action).text
    }

    fun italic(action: ItalicText.() -> Unit) {
        content += ItalicText().apply(action).text
    }
}