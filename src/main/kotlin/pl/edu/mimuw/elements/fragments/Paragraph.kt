package pl.edu.mimuw.elements.fragments


class Paragraph : Fragment() {
    override fun startFragment(builder: StringBuilder) {
        builder.append("\n\n")
    }

    override fun endFragment(builder: StringBuilder) {
        builder.append("\n\n")
    }
}