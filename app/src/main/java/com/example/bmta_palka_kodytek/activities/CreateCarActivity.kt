package com.example.bmta_palka_kodytek.activities

import JsonHandler
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta_palka_kodytek.databinding.ActivityCreateCarBinding
import com.example.bmta_palka_kodytek.model.Car
import com.google.android.material.snackbar.Snackbar

class CreateCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // vytvoří nové auto, uloží do JSON a refreshne aktivitu CarsPresets, aby se tam nově vložené auto zobrazovalo
        val jsonHandler = JsonHandler(this)
        binding.createCarBtn.setOnClickListener {
            val brand = binding.textCustomersCount.text.toString()
            val model = binding.textModel.text.toString()
            val seatsString = binding.textSeats.text.toString()
            val consumptionString = binding.textConsumption.text.toString()

            // Kontrola vstupů
            if (brand.isNotEmpty() && model.isNotEmpty() && seatsString.isNotEmpty() && consumptionString.isNotEmpty()) {
                try {
                    val seats = seatsString.toInt()
                    val consumption = consumptionString.toDouble()

                    // Vytvoření nového auta
                    val newCar = Car().apply {
                        this.brand = brand
                        this.model = model
                        this.seats = seats
                        this.consumption = consumption
                    }

                    // Uložení auta do JSON souboru
                    jsonHandler.writeCar(newCar)

                    // Refresh aktivity CarsPresets
                    startActivity(Intent(this, CarsPresetsActivity::class.java))

                } catch (e: NumberFormatException) {
                    Snackbar.make(binding.root, "Chyba při převodu číselných hodnot.", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(binding.root, "Všechna pole musí být vyplněna.", Snackbar.LENGTH_SHORT).show()
            }
        }

        // zpatky na Cars presets
        binding.createBackToCars.setOnClickListener {
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }
    }
}