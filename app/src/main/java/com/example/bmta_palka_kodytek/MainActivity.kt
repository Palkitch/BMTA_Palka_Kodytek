package com.example.bmta_palka_kodytek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta_palka_kodytek.objects.Auto
import com.example.bmta_palka_kodytek.objects.AutoAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cars)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val autos = listOf(
            Auto().apply {
                brand = "Škoda"
                model = "Octavia"
                seats = 5
                consumption = "6.5 l/100 km"
            },
            Auto().apply {
                brand = "Volkswagen"
                model = "Golf"
                seats = 4
                consumption = "5.8 l/100 km"
            }
            // Přidej další auta podle potřeby
        )

        val adapter = AutoAdapter(autos)
        recyclerView.adapter = adapter

        // Přidej posluchače kliknutí
        adapter.setOnItemClickListener(object : AutoAdapter.OnItemClickListener {
            override fun onItemClick(auto: Auto) {
                // Zpracuj kliknutí na auto
                Toast.makeText(
                    this@MainActivity,
                    "Kliknuto na auto: ${auto.brand} ${auto.model}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
