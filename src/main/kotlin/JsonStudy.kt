import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@JsonClass(generateAdapter = true)
data class Student(
    val name: String,
    val age: Int,
    val friends: List<String>,
    val grades: Map<String, String>
){
    override fun toString(): String {
        return "Student(name='$name', age=$age, friends=$friends, grades=$grades)"
    }
}

@JsonClass(generateAdapter = true)
class Human(
    var name: String,
    var age: Int,
    var friends: List<String>
)

fun main() {


    val moshi = Moshi.Builder().build()

    val human = Human("Lepeha", 52, listOf("Sas", "Nos", "Os"))
    var humanAdapter = moshi.adapter(Human::class.java)
    val humanInJson = humanAdapter.toJson(human)
    println("-----")
    println("We created object of class Human: [$human]")
    println("We converted class to string value of Json format (SERIALIZE) $humanInJson")
    println("First symbol of string: ${humanInJson[0]} ")
    val stringToJson = humanAdapter.fromJson(humanInJson)
    println("We convert string value of Json to Human object back (DESERIALIZE) [$stringToJson] and can get items [${stringToJson?.name}]")
    print("-----\n")

    /**
    * Here we're creating new type of List, that contains Human class objects, to store them in Json format
    * **/
    val listOfHumans = listOf(human, human)
    val type = Types.newParameterizedType(List::class.java, Human::class.java)

    /**
    * can't reassign adapter var (like humanAdapter above), we need to create new one
    * **/
    // humanAdapter = moshi.adapter<List<Human?>>(type)
    val humansAdapter = moshi.adapter<List<Human?>>(type)
    val humansInJson = humansAdapter.toJson(listOfHumans)
    println("Here we created string with value of Json from object of class Human in list (SERIALIZE) $humansInJson")
    print("-----\n")

    val jsonString = """
       [{"name":"Nick","age":10,"friends":["Valery"]},
       {"name":"John","age":25,"friends":[]},
       {"name":"Kate","age":40,"friends":[]}]
       """.trimIndent()
    val newHumansList = humansAdapter.nullSafe().fromJson(jsonString)

    /**
    * We need to double-check null's in List and Human before access them
    * **/
    if (newHumansList != null) {
        val name = newHumansList[0]?.name ?: "empty"
        println("Name: $name")

        for (human in newHumansList) {
            if (human != null) println("${human.name} ${human.age} ${human.friends}")
        }
    }
    print("-----\n")

    val newMoshitype = Types.newParameterizedType(List::class.java, Student::class.java)
    val humanAdapterWithMap = moshi.adapter<List<Student?>>(newMoshitype)
    val jsonStringWithMap = """[{"name":"Ruslan","age":20,"friends":["Victoria","Valery"],
        "grades":{"Math":"A","Philosophy":"F","Physics":"D"}},
        {"name":"Victoria","age":35,"friends":["Ruslan","Anastasia"],
        "grades":{"Math":"B","Philosophy":"C","Physics":"B"}}]""".trimIndent()
    val objectOfStudents = humanAdapterWithMap.fromJson(jsonStringWithMap)
    println(objectOfStudents)

    if (objectOfStudents != null) {
        for (student in objectOfStudents) {
            if (student != null) {
                print(student.grades.keys)
                print(student.grades.values)
            }
        }
    }




}