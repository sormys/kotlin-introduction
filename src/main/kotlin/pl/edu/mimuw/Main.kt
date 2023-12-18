package pl.edu.mimuw
import pl.edu.mimuw.elements.buildSMD
import java.io.File

fun main() {
    println("Hello Kotlin!")
    Hello().greet()
    val path = readln()
    File(path).writeText(buildSMD {
        header1{
            +"Lorem ipsum"
        }
        header2{
            +"dolor sit amet"
        }
        paragraph {
            +"Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            br()
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        }
        paragraph {
            +("Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                    "laboris nisi ut aliquip ex ea commodo consequat.")
        }
        bullet {
            item{
                +"Duis aute irure"
            }
            item{
                +"dolor in reprehenderit"
            }
        }
        list {
            item{
                +"in voluptate velit esse"
            }
            item {
                +"cillum dolore eu fugiat nulla pariatur"
            }
        }
        delimiter()
        paragraph {
            +"Excepteur "
            italic {
                +"sint"
            }
            +" occaecat "
            bold{
                +"cupid\n atat"
            }
            +" non "
            bold{
                italic{
                    +"proident"
                }
            }
            +","
            codeInline{
                +"sunt in culpa"
            }
            codeMultiLine{
                +("qui officia deserunt\n" +
                        "mollit anim id est laborum.")
            }
        }
    })
}
