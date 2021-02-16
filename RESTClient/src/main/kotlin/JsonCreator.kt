import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer


val format = Json { prettyPrint = true }

@Serializable
data class Params(val ObjectUIN: String, val ChangeDate: String,val UserName: String, val Password:String)

@Serializable
 data class MyModel(val id: Int, val jsonRpc: String="2.0", val method: String,val params: Params,  val version: Int)

/* Нижний уровень данные */

@Serializable
data class Root(
   val result: Result
)

@Serializable
data class Result(

    @Serializable(with = dataListSerializer::class)
    val datas: List<data> = arrayListOf()
)

@Serializable
data class data(val ElementName: String, val ElementID: String, val ChangeDate: String)

object dataListSerializer: JsonTransformingSerializer<List<data>>(ListSerializer(data.serializer())) {
    // If response is not an array, then it is a single object that should be wrapped into the array
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
}



class JsonCreator {

    fun createAllObjects(): String{
        val params = Params("", "11.02.2021", "Yana", "erererds323223")
        val test = MyModel(1, "2.0", "GetChangedDictionaryItems", params,  1 )
        return Json.encodeToString(test)
    }

    fun jsonToModel(valueJson: String){
        val data = Json.decodeFromString<Result>(valueJson)
        println(data)
    }

   fun ModelToJson(): String{
       var otvet = Result()

       otvet.datas.plus(data("Barsik", "eee", "2023.30.39"))
       otvet.datas.plus(data("Murka", "eee", "2023.12.39"))
       var root = Root( otvet)

       return Json.encodeToString(root)
   }


}