package pl.edu.mimuw.elements.highlight

import pl.edu.mimuw.elements.TextElement

abstract class TextHighlight(tag: String) : Tag(tag) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class Bold : TextHighlight("**") {
    fun italic(init: Italic.() -> Unit) = initTag(Italic(), init)
}

class Italic : TextHighlight("*") {
    fun bold(init: Bold.() -> Unit) = initTag(Bold(), init)
}

class CodeInline : TextHighlight("`")

class CodeMultiLine: TextHighlight("\n```\n")
