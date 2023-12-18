package pl.edu.mimuw

import pl.edu.mimuw.elements.fragments.SMDBuilder2
import pl.edu.mimuw.lists.BulletPointScope
import pl.edu.mimuw.lists.ListScope
import pl.edu.mimuw.textHighlight.BoldText
import pl.edu.mimuw.textHighlight.ItalicText

@DslMarker
annotation class SMDDsl

@SMDDsl
class SMDBuilder{
    var content: String = ""

    fun build(): String = content
    fun header(level: Int, action: HeaderScope.() -> Unit){
        content += HeaderScope(level).apply(action).header
    }

    fun paragraph(action: ParagraphScope.() -> Unit) {
        content += ParagraphScope().apply(action).paragraph
    }

    fun bullet(action: BulletPointScope.() -> Unit) {
        content += BulletPointScope().apply(action).bulletPoints
    }

    fun list(action: ListScope.() -> Unit) {
        content += ListScope().apply(action).content
    }

    fun code(multiline: Boolean, action: CodeScope.() -> Unit) {
        content += CodeScope(multiline).apply(action).code
    }

    fun bold(action: BoldText.() -> Unit) {
        content += BoldText().apply(action).text
    }

    fun italic(action: ItalicText.() -> Unit) {
        content += ItalicText().apply(action).text
    }

    fun delimiter() {
        content += "\n---\n"
    }
}

fun buildSMD(action: SMDBuilder2.() -> Unit): String =
    SMDBuilder2().apply(action).build()
