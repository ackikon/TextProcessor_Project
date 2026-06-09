import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.SortedMap
import kotlin.io.path.Path
import kotlin.io.path.appendText
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.math.sin
/** TODO: design/develop:
 * - saving manually inputted lines
 * **/

fun inputProcessing(string: String, inputType: String) {
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

    val historyLog =
        History(
            inputType,
            when (inputType) {
                "text" -> string
                "file" -> "file.txt"
                else -> "default"
            },
            string.count(),
            mostUsedCharacters
        )

    val file = Path("./src/main/files/history.json")
    if (!file.exists()) file.createFile()
    if (file.readText().isEmpty()) file.writeText(Json.encodeToString<List<History>>(listOf(historyLog)))
    else {
        val fileContent = Json.decodeFromString<List<History>>(file.readText()).toMutableList()
        fileContent.add(historyLog)
        val json = Json.encodeToString(fileContent)
        file.writeText(json)
    }
}

fun main() {

    println(System.getProperty("user.dir"))
    // Path to file: ./src/main/files/file.txt
    processing@while (true) {

        print("[menu] Enter command (input, history, exit): ")
        when (readln().lowercase()) {
            "input" -> {
                print("[input] Chose input type (manual, file): ")
                when (readln().lowercase()) {
                    "file" -> {
                        print("Enter path to file: ")
                        val fileUrl = readln()
                        val file = Path(fileUrl)
                        if (!file.exists()) println("File is not found!")
                        else {
                            val fileContent = file.readText().dropLast(1)
                            // println(fileContent)
                            if (!fileContent.isEmpty()) inputProcessing(fileContent, "file") else println("Empty file")
                        }
                    }
                    "manual" -> {
                        val processedString = stringManualInput()
                        if (!processedString.isEmpty()) inputProcessing(processedString, "text") else println("Empty line")
                    }
                    else -> println("Invalid input")
                }
            }
            "history" -> {
                val file = Path("./src/main/files/history.json")
                if (!file.exists()) println("File is not found!")
                else if (file.readText().isEmpty()) println("Empty lines")
                else {
                    val listOfHistory = Json.decodeFromString<List<History>>(file.readText())
                    println("== History ==")
                    for (line in listOfHistory) {
                        if (line.type == "text") println("\nText: \n${line.content}\n") else println("\nText: ${line.content}\n")
                        println("""
                            Characters: ${line.length}
                            Most used characters: ${line.mostUsed.keys.joinToString(", ")} (${line.mostUsed.values.first()} times)
                            
                        """.trimIndent())
                    }
                }
            }
            "exit", "" -> {
                break@processing
            }
            else -> println("Invalid input")
        }
    }
    print("Bye!")
}