package com.example.bmta_palka_kodytek.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta_palka_kodytek.databinding.ActivityCreateCarBinding

class CreateCarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // vytvoří nové auto, uloží do JSON a refreshne aktivitu CarsPresets, aby se tam nově vložené auto zobrazovalo
        binding.createCarBtn.setOnClickListener {
            // TODO odeslat nově vytvořené auto do JSONU a znovu je načist v cars preset
            // zahrnout zde i kontrolu vstupů
        }

        // zpatky na Cars presets
        binding.createBackToCars.setOnClickListener {
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }
    }
}