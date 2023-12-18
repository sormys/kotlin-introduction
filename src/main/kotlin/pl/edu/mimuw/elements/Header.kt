package pl.edu.mimuw.elements

import pl.edu.mimuw.elements.Lists.BulletPoint
import pl.edu.mimuw.elements.Lists.NumberList
import pl.edu.mimuw.elements.highlight.Bold
import pl.edu.mimuw.elements.highlight.CodeInline
import pl.edu.mimuw.elements.highlight.CodeMultiLine
import pl.edu.mimuw.elements.highlight.Italic

sealed class Header(val tag: String): SMDElement() {
    override fun render(builder: StringBuilder) {
        for (c in children) {
            c.render(builder)
        }
        BreakLine().render(builder)
        builder.append(tag)
        BreakLine().render(builder)
    }

    fun italic(init: Italic.() -> Unit) = initTag(Italic(), init)

    fun bold(init: Bold.() -> Unit) = initTag(Bold(), init)

    fun codeInline(init: CodeInline.() -> Unit) = initTag(CodeInline(), init)
}

class Header1 : Header("=")

class Header2 : Header("-")