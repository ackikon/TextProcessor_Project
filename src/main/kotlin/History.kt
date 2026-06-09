import kotlinx.serialization.Serializable

@Serializable
class History(
    val type: String = "default",
    val content: String = "default",
    val length: Int = 0,
    val mostUsed: Map<Char, Int> = emptyMap(),
) {


    override fun toString(): String {
        return "History(type='$type', content= '$content', length='$length', mostUsed='$mostUsed')"
    }

}
