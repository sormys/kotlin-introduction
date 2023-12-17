package pl.edu.mimuw.textHighlight

import pl.edu.mimuw.SMDDsl

@SMDDsl
class BoldText(currentHighlight: Highlighting = Highlighting()): TextHighlight(currentHighlight) {
    override val tag: String = "**"
    override var content: String = ""
    override val recursive: Boolean
        get() = currentHighlight.isHighlighted(Highlighting.HighlightType.BOLD)

    fun italic(action: ItalicText.() -> Unit) {
        content += ItalicText(currentHighlight.add(Highlighting.HighlightType.BOLD)).apply(action).text
    }
}