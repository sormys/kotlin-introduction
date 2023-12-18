package pl.edu.mimuw.elements.fragments

import pl.edu.mimuw.*
import pl.edu.mimuw.elements.BreakLine
import pl.edu.mimuw.elements.highlight.Bold
import pl.edu.mimuw.elements.highlight.CodeInline
import pl.edu.mimuw.elements.highlight.CodeMultiLine
import pl.edu.mimuw.elements.highlight.Italic
import pl.edu.mimuw.elements.Lists.BulletPoint
import pl.edu.mimuw.elements.Lists.NumberList
import pl.edu.mimuw.elements.SMDElement
import pl.edu.mimuw.elements.TextElement

@SMDDsl2
abstract class Fragment() : SMDElement() {

    abstract fun startFragment(builder: StringBuilder)

    abstract fun endFragment(builder: StringBuilder)

    override fun render(builder: StringBuilder) {
        startFragment(builder)
        for (c in children) {
            c.render(builder)
        }
        endFragment(builder)
    }

    fun italic(init: Italic.() -> Unit) = initTag(Italic(), init)

    fun bold(init: Bold.() -> Unit) = initTag(Bold(), init)

    fun codeInline(init: CodeInline.() -> Unit) = initTag(CodeInline(), init)

    fun codeMultiLine(init: CodeMultiLine.() -> Unit) = initTag(CodeMultiLine(), init)

    fun list(init: NumberList.() -> Unit) = initTag(NumberList(), init)

    fun bullet(init: BulletPoint.() -> Unit) = initTag(BulletPoint(), init)

    fun delimiter() = children.add(TextElement("\n---\n"))

    fun br() = children.add(BreakLine())
}