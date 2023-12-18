package pl.edu.mimuw.elements.lists

import pl.edu.mimuw.elements.SMDDsl2
import pl.edu.mimuw.elements.SMDElement
import pl.edu.mimuw.elements.TextElement
import pl.edu.mimuw.elements.highlight.Bold
import pl.edu.mimuw.elements.highlight.CodeInline
import pl.edu.mimuw.elements.highlight.CodeMultiLine
import pl.edu.mimuw.elements.highlight.Italic

@SMDDsl2
class Item : SMDElement() {

    override fun render(builder: StringBuilder) = children.forEach {
        it.render(builder)
    }

    operator fun String.unaryPlus() =
        children.add(TextElement(this))

    fun italic(init: Italic.() -> Unit) = initTag(Italic(), init)

    fun bold(init: Bold.() -> Unit) = initTag(Bold(), init)

    fun codeInline(init: CodeInline.() -> Unit) = initTag(CodeInline(), init)

    fun codeMultiLine(init: CodeMultiLine.() -> Unit) = initTag(CodeMultiLine(), init)
}