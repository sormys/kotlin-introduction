package pl.edu.mimuw.elements.fragments

import pl.edu.mimuw.elements.TextElement

class Paragraph : Fragment() {
    override fun startFragment(builder: StringBuilder) {
        builder.append("\n\n")
    }

    override fun endFragment(builder: StringBuilder) {
        builder.append("\n\n")
    }

    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }

}