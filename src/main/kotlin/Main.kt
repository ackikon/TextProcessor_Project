import java.util.SortedMap
import kotlin.math.sin
/** TODO: design/develop:
 * - history route
 * - exit route
 * - file route
 * - wrong command route (-> dislocate him to start position)
 * **/

fun inputProcessing(string: String) {
    val input = string.filter { it != ' ' }
    val mapOfSymbols = input.associateWith { key -> input.count() { char -> char == key } }
    val mostUsedCharacters = mapOfSymbols
        .filterValues { it == mapOfSymbols.values.max() }
        .filterKeys { it != '\n' } // remove "new line" from collection

    println("Characters: ${string.count()}")
    print("Most used characters: ")
    if (mostUsedCharacters.values.first() < 2) println("-")
    else {
        print(mostUsedCharacters.keys.joinToString(", "))
        println(" (${mostUsedCharacters.values.first()} times)")
    }
}

fun main() {

    processing@while (true) {

        print("[menu] Enter command (input, history, exit): ")
        val firstCommand = readln()

        // develop history and exit variants
        if ((firstCommand.lowercase() == "exit" || firstCommand == "")) break@processing

        print("[input] Chose input type (manual, file): ")
        val secondCommand = readln()

        if (secondCommand.lowercase() == "manual") {
            val processedString = stringManualInput()
            if (!processedString.isEmpty()) inputProcessing(processedString) else println("Empty line")
        }
        else if (secondCommand.lowercase() == "file") {
            print("Enter path to file: ")
            // develop file input
        }
        else println("Invalid input")




    }

    val b = 1..< 4
    print("Bye!")
}