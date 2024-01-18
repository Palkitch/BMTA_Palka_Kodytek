package com.example.bmta_palka_kodytek.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmta_palka_kodytek.R
import com.example.bmta_palka_kodytek.databinding.ActivityCarsPresetsBinding
import com.example.bmta_palka_kodytek.databinding.ActivityCreateCarBinding

class CreateCarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // vytvoří nové auto, uloží do JSON a refreshne aktivitu CarsPresets
        binding.createCarBtn.setOnClickListener {
            // TODO odeslat nově vytvořené auto do JSONU a znovu je načist v cars preset
        }

        // zpatky na Cars presets
        binding.createBackToCars.setOnClickListener {
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }
    }
}