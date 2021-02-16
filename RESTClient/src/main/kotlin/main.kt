import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


fun main(args: Array<String>) {

//  val getAllObjectsJson = JsonCreator().createAllObjects()
//  val client = HttpClient()
//  val request = client.PostData("http://192.168.100.125:8000/api/spravochnik/", getAllObjectsJson)
  //JsonCreator().jsonToModel(request)
  print(JsonCreator().ModelToJson())
}