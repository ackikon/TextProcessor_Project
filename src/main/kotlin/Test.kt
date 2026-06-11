import jdk.internal.joptsimple.util.RegexMatcher.regex
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    /*val file = Path("./src/main/files/history.json")
    val json = file.readText()*/

    /*val unformattedStringList = listOf(
        "Here is a list :item one ,item two .",
        "Apples , bananas ; and { oranges }  .",
        "Wait ,what ?I [ didn't ] know that !",
        "This line    isn't   following  ( space ) formatting  rules  .It must  be formatted  .")*/

    val unformattedStringList = listOf(
        // 1. Consecutive Spaces
        " This  sentence   has    way     too      many spaces.",
        " Just  testing  two  spaces  between  words.",

        // 2. Punctuation Marks (. , : ; ? !)
        "Wait ,what ?I didn't know that !",
        "Apples , bananas ; and oranges .",
        "Here is a list :item one ,item two .",
        "Multiple marks together  !  ?  .  ",

        // 3. Start, End, and Newline Spaces
        "   This string starts with spaces.",
        "This string ends with spaces.   ",
        "Line one.\n  Line two starts with spaces.",
        "  \n   Spaces everywhere   \n  ",

        // 4. Inside Parentheses, Brackets, and Braces
        "Let's test some ( parenthesis ).",
        "Here is an array [ 1, 2, 3 ].",
        "Here is an object { key: value }.",
        "Nested ( [ { spaces } ] ) test.",

        // 5. Outside Parentheses, Brackets, and Braces
        "This function(parameter)returns true.",
        "Look at this[bracket]test.",
        "The data{values}needs spacing.",

        // 6. "Boss Level" Tests (Multiple rules broken at once)
        "   Hello ,world  !This is a test  (   a very bad one   )  .It needs[formatting]badly ; fix it  . \n  Please ?  ",
        "  Data{ id:1 , name: test }is processed ( successfully ).Check the logs[ error ]for details .  "
    )


    for (unformattedString in unformattedStringList) {

        var formattedString = unformattedString.replace("\\s*(?<value>\\S+)\\s*".toRegex(), "\${value} ")
        // old code
        // formattedString = formattedString.replace("^\\s".toRegex(), "")

        // old code
        // formattedString = formattedString.replace("\\s?(?<value>[(\\[{])\\s?".toRegex(), " \${value}")
        // old code
        // formattedString = formattedString.replace("\\s?(?<value>[)\\]}])\\s?".toRegex(), "\${value} ")

        // old code
        // formattedString = formattedString.replace("(?<value1>[()\\[\\]{}]+) (?<value2>[()\\[\\]{}]+)".toRegex(), "\${value1}\${value2}")
        // old code
        // formattedString = formattedString.replace("(?<value1>[()\\[\\]{}]+) (?<value2>[()\\[\\]{}]+)".toRegex(), "\${value1}\${value2}")

        // search left-side bracket and "end" symbols with whitespace after to remove whitespace
        formattedString = formattedString.replace("(?<value>[(\\[{!.?]+) ".toRegex(), "\${value}")
        // search right-side bracket and "end" symbols with whitespace after to remove whitespace
        formattedString = formattedString.replace(" (?<value>[)\\]}!.?]+)".toRegex(), "\${value}")

        // search any symbol except left-side bracket or dash with left-side bracket or dash after it and make exactly one whitespace between them
        formattedString = formattedString.replace("(?<value1>[^({\\[-])\\s*(?<value2>[({\\[-])".toRegex(), "\${value1} \${value2}")
        // search right-side bracket or dash with any symbol except right-side bracket or dash after it and make exactly one whitespace between them
        formattedString = formattedString.replace("(?<value1>[)}\\]-])\\s*(?<value2>[^)}\\]-])".toRegex(), "\${value1} \${value2}")
        //formattedString = formattedString.replace("\\s*(?<value>[^a-zA-Z0-9\\s'()\\[\\]{}])\\s*".toRegex(), "\${value} ")
        //formattedString = formattedString.dropLast(1)

        println(formattedString)
    }



}