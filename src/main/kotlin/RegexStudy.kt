import kotlinx.serialization.descriptors.StructureKind

fun main() {

    /**
     * In the case to use "escape" character (\ - slash) to search LITERALLY question mark (?) in kotlin language space
     * we need to use "escape" character on "escape" character like - \\
     * Regex format: \? (escape character regex (\) of question mark (preceding character or nothing))
     * Kotlin format: \\? (escape character kotlin (\) of escape character regex (\) of question mark)
     * **/


    val colorRegex = Regex("colou?rs?")
    for (string in listOf("color", "coloer", "colour", "colouer", "colors", "coloers"))
        println("$string is ${colorRegex.containsMatchIn(string)}")

    println("\n-----\n")

    val stringToRegex = "cats\\?"
    val regex1 = stringToRegex.toRegex()
    val regex2 = Regex("cats\\?")
    val regex3 = Regex(".?")

    for (string in listOf("oscats", "procats?", "bads?", "catss?", "ccats?", "cats?das", "a"))
        println("$string is ${regex2.containsMatchIn(string)}")

    println("\n-----\n")

    /**
     * Caret symbol (^) - mean start of a line
     * Double slash d (\\d) - mean "escape" kotlin symbol of "escape" regex symbol of digital symbol
     * **/

    val regex4 = Regex("^\\d .")
    val report = "0 wrong answers"
    println("Report is ${regex4.containsMatchIn(report)}")

    println("\n-----\n")

    /**
     * Curly brackets with number ({2}) - means preceding instruction (symbol) will be repeated exact number of times
     * **/

    val regexExactTimes = Regex(".{5}")
    for (string in listOf("a", "ab", "abc", "abcd", "abcde", "ds as"))
        println("$string is ${regexExactTimes.matches(string)}")

    println("\n-----\n")

    /**
     * Brackets () - separates symbols with same instruction (example: can'?t? -> can('t)?)
     * **/

    val answer = listOf("I can't do my homework on time!", "I could do my homework on time!", "I can do my homework on time!")
    val regex = Regex("I can('t)? do my homework on time!")
    for (string in answer)
        println("$string is ${regex.containsMatchIn(string)}")
}