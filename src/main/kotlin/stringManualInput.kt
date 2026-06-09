fun stringManualInput(): String {
    val stringBuilder = StringBuilder()
    while(true) {
        print("Enter text (enter a blank line to end): ")
        val appendString = readln()
        if (appendString == "") break
        else {
            stringBuilder.appendLine(appendString)
        }
    }
    return stringBuilder.toString().dropLast(1)
}