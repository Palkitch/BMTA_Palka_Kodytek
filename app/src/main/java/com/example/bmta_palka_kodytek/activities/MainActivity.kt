package com.example.bmta_palka_kodytek.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta_palka_kodytek.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        // spuštění CarsPresetsActivity
        binding.buttonCars.setOnClickListener {
            startActivity(Intent(this, CarsPresetsActivity::class.java))
        }

        // spuštění Calculate activity
        binding.buttonCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateActivity::class.java))
        }

        // vypnutí app
        binding.buttonExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        }
    }
}