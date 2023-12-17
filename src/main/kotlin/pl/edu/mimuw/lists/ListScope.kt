package pl.edu.mimuw.lists

import pl.edu.mimuw.SMDDsl

@SMDDsl
class ListScope(){

    private var items: MutableList<String> = mutableListOf()
    val content: String
        get() = (items zip 1 .. items.size).joinToString(separator="") {
            "${it.second}. ${it.first.replace("^\\s+".toRegex(), "")}\n"
        }

    fun item(action: ListItemScope.() -> Unit) {
        items.add(ListItemScope().apply(action).content)
    }

}