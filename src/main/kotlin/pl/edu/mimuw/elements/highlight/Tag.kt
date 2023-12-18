package pl.edu.mimuw.elements.highlight

import pl.edu.mimuw.elements.SMDDsl2
import pl.edu.mimuw.elements.SMDElement

@SMDDsl2
abstract class Tag(private val tagSymbol: String) : SMDElement() {

    override fun render(builder: StringBuilder) {
        builder.append(tagSymbol)
        for (c in children) {
            c.render(builder)
        }
        builder.append(tagSymbol)
    }

}