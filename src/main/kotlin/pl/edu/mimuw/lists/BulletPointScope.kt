package pl.edu.mimuw.lists

import pl.edu.mimuw.SMDDsl

@SMDDsl
class BulletPointScope(){

    private var bps: MutableList<String> = mutableListOf()
    val bulletPoints: String
        get() = bps.joinToString(separator="") {
            "- ${it.replace("^\\s+".toRegex(), "")}\n"
        }

    fun item(action: ListItemScope.() -> Unit) {
        bps.add(ListItemScope().apply(action).content)
    }

}