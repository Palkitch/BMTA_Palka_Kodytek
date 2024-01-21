package com.example.bmta_palka_kodytek.activities

import JsonHandler
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta_palka_kodytek.databinding.ActivityCreateCarBinding
import com.example.bmta_palka_kodytek.model.Car

class CreateCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // vytvoří nové auto, uloží do JSON a refreshne aktivitu CarsPresets, aby se tam nově vložené auto zobrazovalo
        val jsonHandler = JsonHandler(this)
        binding.createCarBtn.setOnClickListener {
            // TODO odeslat nově vytvořené auto do JSONU a znovu je načist v cars preset
            // zahrnout zde i kontrolu vstupů

            // Vytvoření nového auta
            val newCar = Car().apply {
                brand = binding.textCustomersCount.text.toString()
                model = binding.textModel.text.toString()
                seats = binding.textSeats.text.toString().toInt()
                consumption = binding.textConsumption.text.toString().toDouble()
            }
            // Uložení auta do JSON souboru
            jsonHandler.writeCar(newCar)

            // Refresh aktivity CarsPresets
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }

        // zpatky na Cars presets
        binding.createBackToCars.setOnClickListener {
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }
    }
}