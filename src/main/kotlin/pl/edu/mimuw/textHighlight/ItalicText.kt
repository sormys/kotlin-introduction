package pl.edu.mimuw.textHighlight

import pl.edu.mimuw.SMDDsl

@SMDDsl
class ItalicText(currentHighlight: Highlighting = Highlighting()): TextHighlight(currentHighlight) {
    override val tag: String = "*"
    override var content: String = ""
    override val recursive: Boolean
        get() = currentHighlight.isHighlighted(Highlighting.HighlightType.ITALIC)


    fun bold(action: BoldText.() -> Unit) {
        content += BoldText(currentHighlight.add(Highlighting.HighlightType.ITALIC)).apply(action).text
    }
}