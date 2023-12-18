package pl.edu.mimuw.elements.lists

import pl.edu.mimuw.elements.SMDDsl
import pl.edu.mimuw.elements.SMDElement

@SMDDsl
abstract class SMDList(private val tagLambda: (Int) -> String) : SMDElement() {

    override fun render(builder: StringBuilder) =
        children.forEachIndexed { index, c ->
            builder.append("${tagLambda(index + 1)} ")
            c.render(builder)
            builder.append("\n")
        }


    fun item(init: Item.() -> Unit) = initTag(Item(), init)
}