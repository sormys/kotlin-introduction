package pl.edu.mimuw.elements.fragments

import pl.edu.mimuw.elements.BreakLine
import pl.edu.mimuw.elements.lists.BulletPoint
import pl.edu.mimuw.elements.lists.NumberList
import pl.edu.mimuw.elements.SMDDsl2
import pl.edu.mimuw.elements.SMDElement
import pl.edu.mimuw.elements.TextElement
import pl.edu.mimuw.elements.highlight.Bold
import pl.edu.mimuw.elements.highlight.CodeInline
import pl.edu.mimuw.elements.highlight.CodeMultiLine
import pl.edu.mimuw.elements.highlight.Italic

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

    operator fun String.unaryPlus() =
        children.add(TextElement(this))

    fun italic(init: Italic.() -> Unit) = initTag(Italic(), init)

    fun bold(init: Bold.() -> Unit) = initTag(Bold(), init)

    fun codeInline(init: CodeInline.() -> Unit) = initTag(CodeInline(), init)

    fun codeMultiLine(init: CodeMultiLine.() -> Unit) = initTag(CodeMultiLine(), init)

    fun list(init: NumberList.() -> Unit) = initTag(NumberList(), init)

    fun bullet(init: BulletPoint.() -> Unit) = initTag(BulletPoint(), init)

    fun delimiter() = children.addAll(listOf(BreakLine(), TextElement("---"), BreakLine()))
}