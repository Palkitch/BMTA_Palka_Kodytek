package com.example.bmta_palka_kodytek.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta_palka_kodytek.databinding.ActivityCalculateBinding
import com.example.bmta_palka_kodytek.model.Car
import com.google.android.material.snackbar.Snackbar

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCalculateBinding
    private var selectedCar : Car? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)  // kvuli intentu, prostě to tu musí být :D
    @SuppressLint("SetTextI18n")    // potlačení "string joinů"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // zpatky na main page
        binding.calcBtnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // získání předaného selectedCar ze třídy CarPresetsActivity
        selectedCar = intent.getSerializableExtra("selectedCar", Car::class.java)

        // přiřazení funkce na výpočet ceny po stisknutí tlačítka
        binding.btnCountMoney.setOnClickListener {
            binding.textResult.text = "Cena pro jednotlivce = ${calculateResult()},- Kč"
        }

        // Pro test zkusime auto vypsat do textView
        if (selectedCar != null) {
            binding.tvHint.visibility = View.INVISIBLE  // zneviditelníme nápovědu
            binding.tvHint.layoutParams.height = 0  // nastavit nápovědě výšku na 0, aby nezabírala místo
            binding.brandTextView.text = "Značka: ${selectedCar!!.brand}"
            binding.modelTextView.text = "Model: ${selectedCar!!.model}"
            binding.seatsTextView.text = "Počet sedadel: ${selectedCar!!.seats}"
            binding.consumptionTextView.text = "Spotřeba: ${selectedCar!!.consumption}"
        } else {
            binding.tvHint.visibility = View.VISIBLE  // zviditelníme nápovědu
        }
    }


    // funkce která spočítá cenu pro taxikáře co zaplatí jednotlivec
    private fun calculateResult(): String {
        try {
            val numOfCustomers = binding.textCustomersCount.text.toString().toInt()
            val km = binding.textKm.text.toString().toInt()
            val salary = binding.textSalary.text.toString().toInt()
            val priceOfGasPerLiter = 37
            selectedCar?.let { car ->
                if (numOfCustomers > car.seats) {
                    Snackbar.make(binding.root, "Chyba: Tolik lidí se do tohoto automobilu nevejde", Snackbar.LENGTH_LONG).show()
                    return ""
                }
                // projeté litry = (ujeté km/100) * spotřeba auta
                val consumedLiters = (km / 100.0) * car.consumption.toDouble()

                // cena za spotřebu = (projeté litry * cena paliva za litr) / počtem osob
                val priceForConsumption = (consumedLiters * priceOfGasPerLiter).toInt() / numOfCustomers

                // cena za práci = (cena za km * ujeté km) / počtem osob
                val priceForWork = (km * salary) / numOfCustomers

                val finalPrice = priceForConsumption + priceForWork
                return finalPrice.toString()
            }

        } catch (e: NumberFormatException) {
            Snackbar.make(binding.root, "Chyba: Vstupní hodnoty musí být celá čísla", Snackbar.LENGTH_LONG).show()
        }
        return ""
    }   // snackbar zobrazí text na krátkou dobu na spodu obrazovky v aplikaci
}