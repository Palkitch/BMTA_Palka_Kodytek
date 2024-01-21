import android.content.Context
import com.example.bmta_palka_kodytek.model.Car
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class JsonHandler(private val context: Context) {

    private val fileName = "cars.json"

    fun readCars(): List<Car> {
        try {
            val filePath = File(context.filesDir, fileName)
            val jsonContent = filePath.bufferedReader().use(BufferedReader::readText)
            val jsonArray = JSONArray(jsonContent)
            val cars = mutableListOf<Car>()

            for (i in 0 until jsonArray.length()) {
                val carObject = jsonArray.getJSONObject(i)
                val car = Car().apply {
                    brand = carObject.getString("brand")
                    model = carObject.getString("model")
                    seats = carObject.getInt("seats")
                    consumption = carObject.getDouble("consumption")
                }

                cars.add(car)
            }

            return cars
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun writeCar(car: Car) {
        try {
            val filePath = File(context.filesDir, fileName)
            val jsonContent = if (filePath.exists()) {
                filePath.bufferedReader().use(BufferedReader::readText)
            } else {
                "[]"
            }

            val jsonArray = JSONArray(jsonContent)

            val carObject = JSONObject().apply {
                put("brand", car.brand)
                put("model", car.model)
                put("seats", car.seats)
                put("consumption", car.consumption)
            }

            jsonArray.put(carObject)

            // Zapisujeme pouze nově přidané auto, ne celý seznam
            val outputStreamWriter = OutputStreamWriter(
                context.openFileOutput(fileName, Context.MODE_PRIVATE)
            )
            outputStreamWriter.write(jsonArray.toString())
            outputStreamWriter.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
