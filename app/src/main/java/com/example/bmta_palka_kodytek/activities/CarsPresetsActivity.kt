package com.example.bmta_palka_kodytek.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmta_palka_kodytek.adapter.CarAdapter
import com.example.bmta_palka_kodytek.databinding.ActivityCarsPresetsBinding
import com.example.bmta_palka_kodytek.model.Car

class CarsPresetsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCarsPresetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarsPresetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // zpatky na main page
        binding.carsBtnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.carsCreateNew.setOnClickListener {
            startActivity(Intent(this, CreateCarActivity::class.java))
        }

        // TODO: zde udělej nějakou instanci toho sveho JSON loaderu a naplň tím ten list
        val carList = mutableListOf<Car>()

        // Přidejte auta do seznamu
        carList.add(Car().apply {
            brand = "Toyota"
            model = "Camry"
            seats = 5
            consumption = "8.5 L/100km"
        })
        carList.add(Car().apply {
            brand = "Honda"
            model = "Civic"
            seats = 5
            consumption = "7.2 L/100km"
        })

        // Vytvoření adaptéru s anonymní funkcí jako parametrem pro CarAdapter konstruktor, tahle funkce
        // bude definovat akorát předání vybraného itemu z recycler view (Car) další aktivitě, a to CalculateActivity
        // aby se s jejími parametry dalo rovnou pracovat
        val adapter = CarAdapter(carList) { selectedCar ->
            val intent = Intent(this, CalculateActivity::class.java)
            intent.putExtra("selectedCar", selectedCar)
            startActivity(intent)
        }

        // Nastavení našeho adapteru do recycler view
        binding.carsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.carsRecyclerView.adapter = adapter
    }
}