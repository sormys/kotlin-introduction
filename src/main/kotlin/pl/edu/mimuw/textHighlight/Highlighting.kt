package pl.edu.mimuw.textHighlight

import pl.edu.mimuw.SMDDsl

@SMDDsl
data class Highlighting(
    val highlights: Set<HighlightType> = emptySet()
) {
    enum class HighlightType {
        BOLD,
        ITALIC,
    }

    fun isHighlighted(type: HighlightType): Boolean = highlights.contains(type)

    fun add(type: HighlightType): Highlighting = Highlighting(highlights.plus(type))
}