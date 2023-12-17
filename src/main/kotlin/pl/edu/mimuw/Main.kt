package pl.edu.mimuw
import java.io.File

fun main() {
    println("Hello Kotlin!")
    Hello().greet()
    val path = readln()
    File(path).writeText(buildSMD {
        header(1){
            +"Lorem ipsum"
        }
        header(2){
            +"dolor sit amet"
        }
        paragraph {
            +("Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n" +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
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
            code(false){
                +"sunt in culpa"
            }
            code(true){
                +("qui officia deserunt\n" +
                        "mollit anim id est laborum.")
            }
        }
    })
}
