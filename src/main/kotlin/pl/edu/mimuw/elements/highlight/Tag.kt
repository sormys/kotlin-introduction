package pl.edu.mimuw.elements.highlight

import pl.edu.mimuw.elements.SMDDsl
import pl.edu.mimuw.elements.SMDElement

@SMDDsl
abstract class Tag(private val tagSymbol: String) : SMDElement() {

    override fun render(builder: StringBuilder) {
        builder.append(tagSymbol)
        for (c in children) {
            c.render(builder)
        }
        builder.append(tagSymbol)
    }

}