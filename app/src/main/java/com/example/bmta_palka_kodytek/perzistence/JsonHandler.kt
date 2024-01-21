import android.content.Context
import com.example.bmta_palka_kodytek.model.Car
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class JsonHandler(private val context: Context) {

    // Název souboru pro ukládání dat
    private val fileName = "cars.json"

    // Metoda pro načtení seznamu aut z JSON souboru
    fun readCars(): List<Car> {
        try {
            // Vytvoření cesty k souboru
            val filePath = File(context.filesDir, fileName)

            // Přečtení obsahu souboru pomocí BufferedReader
            val jsonContent = filePath.bufferedReader().use(BufferedReader::readText)

            // Vytvoření JSON pole ze získaného obsahu
            val jsonArray = JSONArray(jsonContent)

            // Seznam pro ukládání načtených aut
            val cars = mutableListOf<Car>()

            // Procházení JSON pole a vytváření objektů Car
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

            // Vrácení seznamu aut
            return cars
        } catch (e: Exception) {
            e.printStackTrace()

            // V případě chyby vrátí prázdný seznam
            return emptyList()
        }
    }

    // Metoda pro zápis nového auta do JSON souboru
    fun writeCar(car: Car) {
        try {
            // Vytvoření cesty k souboru
            val filePath = File(context.filesDir, fileName)

            // Přečtení obsahu souboru (pokud existuje)
            val jsonContent = if (filePath.exists()) {
                filePath.bufferedReader().use(BufferedReader::readText)
            } else {
                "[]" // Pokud soubor neexistuje, vytvoří se prázdné JSON pole
            }

            // Vytvoření JSON pole ze získaného obsahu
            val jsonArray = JSONArray(jsonContent)

            // Vytvoření JSON objektu pro nové auto
            val carObject = JSONObject().apply {
                put("brand", car.brand)
                put("model", car.model)
                put("seats", car.seats)
                put("consumption", car.consumption)
            }

            // Přidání nového objektu do JSON pole
            jsonArray.put(carObject)

            // Zápis nového obsahu (pouze nově přidané auto) zpět do souboru
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
