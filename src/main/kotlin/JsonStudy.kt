import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class Student(
    val name: String,
    val friends: List<String>,
    val grades: Map<String, String>
){
    val age: Int = 0
    override fun toString(): String {
        return "Student(name='$name', age=$age, friends=$friends, grades=$grades)"
    }
}

@Serializable
class Human(
    var name: String,
    var age: Int,
    var friends: List<String>
)

fun main() {

    val human = Human("Lepeha", 52, listOf("Sas", "Nos", "Os"))
    val humanData = Json.encodeToString(human)
    println("-----")
    println("We created object of class Human: [$human]")
    println("We converted class to string value of Json format (SERIALIZE) $humanData")
    println("First symbol of string: ${humanData[0]} ")
    val stringToJson = Json.decodeFromString<Human>(humanData)
    println("We convert string value of Json to Human object back (DESERIALIZE) [$stringToJson] and can get items [${stringToJson.name}]")
    print("-----\n")

    /**
    * Here we're creating new type of List, that contains Human class objects, to store them in Json format
    * **/
    val listOfHumans = listOf(human, human)
    val dataHumans = Json.encodeToString(listOfHumans)
    println("Here we created string with value of Json from object of class Human in list (SERIALIZE) $dataHumans")
    print("-----\n")

    val jsonString = """
       [{"name":"Nick","age":10,"friends":["Valery"]},
       {"name":"John","age":25,"friends":[]},
       {"name":"Kate","age":40,"friends":[]}]
       """.trimIndent()
    val newHumansList = Json.decodeFromString<List<Human>>(jsonString)

    val name = newHumansList[0].name
    println("Name: $name")

    for (human in newHumansList) {
        println("${human.name} ${human.age} ${human.friends}")
    }

    print("-----\n")

    val jsonStringWithMap = """
        [{"name":"Ruslan","age":20,"friends":["Victoria","Valery"],
        "grades":{"Math":"A","Philosophy":"F","Physics":"D"}},
        {"name":"Victoria","age":35,"friends":["Ruslan","Anastasia"],
        "grades":{"Math":"B","Philosophy":"C","Physics":"B"}}]
        """.trimIndent()
    val objectOfStudents = Json.decodeFromString<List<Student>>(jsonStringWithMap)
    println(objectOfStudents)


    for (student in objectOfStudents) {
        print(student.grades.keys)
        print(student.grades.values)
    }






}