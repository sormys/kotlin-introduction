package pl.edu.mimuw.elements

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder) {
        builder.append(text.replace("\n", " "))
    }
}

