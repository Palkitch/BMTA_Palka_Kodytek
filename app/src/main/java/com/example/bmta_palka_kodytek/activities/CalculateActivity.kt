package com.example.bmta_palka_kodytek.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.bmta_palka_kodytek.databinding.ActivityCalculateBinding
import com.example.bmta_palka_kodytek.model.Car

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCalculateBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)  // kvuli intentu, prostě to tu musí být :D
    @SuppressLint("SetTextI18n")    // potlačení "string joinů" (zatim jen na test)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater);
        setContentView(binding.root)

        // zpatky na main page
        binding.calcBtnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Získání předaného selected car ze třídy CarPresetsActivity
        val selectedCar = intent.getSerializableExtra("selectedCar", Car::class.java)

        // Pro test zkusime auto vypsat do textView
        if (selectedCar != null) {
            val brand = selectedCar.brand
            val model = selectedCar.model
            val seats = selectedCar.seats
            val consumption = selectedCar.consumption
            binding.testTv.text = "$brand, $model, $seats, $consumption"
        } else {
            binding.testTv.text = "Vybrané auto je null";
        }
    }
}