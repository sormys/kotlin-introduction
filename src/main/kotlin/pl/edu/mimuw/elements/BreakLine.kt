package pl.edu.mimuw.elements

class BreakLine: Element {
    override fun render(builder: StringBuilder) {
        builder.append(("  \n"))
    }
}