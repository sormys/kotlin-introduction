package pl.edu.mimuw.elements.fragments

import pl.edu.mimuw.SMDDsl2
import pl.edu.mimuw.elements.Header1
import pl.edu.mimuw.elements.Header2
import pl.edu.mimuw.elements.TextElement

@SMDDsl2
class SMDBuilder2: Fragment() {
    override fun startFragment(builder: StringBuilder) {}

    override fun endFragment(builder: StringBuilder) {}

    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }

    fun paragraph(init: Paragraph.() -> Unit) = initTag(Paragraph(), init)

    fun header1(init: Header1.() -> Unit) = initTag(Header1(), init)

    fun header2(init: Header2.() -> Unit) = initTag(Header2(), init)

    fun build() = toString()
}