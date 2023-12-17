package pl.edu.mimuw.textHighlight

import pl.edu.mimuw.SMDDsl


@SMDDsl
sealed class TextHighlight(protected val currentHighlight: Highlighting) {
    protected abstract var content: String
    protected abstract val tag: String
    protected abstract val recursive: Boolean

    operator fun String.unaryPlus() {
        content += this.replace("\n+".toRegex(), "  \n")
    }
    val text: String
        get() = if (!recursive && content.isNotEmpty()) "$tag${content.replace("^\\s+".toRegex(), "")}$tag" else content
}

