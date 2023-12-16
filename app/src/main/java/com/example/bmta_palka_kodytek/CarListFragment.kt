// ve třídě CarListFragment
package com.example.bmta_palka_kodytek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta_palka_kodytek.objects.Car
import com.example.bmta_palka_kodytek.objects.CarAdapter

class CarListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializace RecyclerView pro fragment
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val autos = listOf(
            Car().apply {
                brand = "Škoda"
                model = "Octavia"
                seats = 5
                consumption = "6.5 l/100 km"
            },
            Car().apply {
                brand = "Volkswagen"
                model = "Golf"
                seats = 4
                consumption = "5.8 l/100 km"
            }
            // Přidej další auta podle potřeby
        )

        val adapter = CarAdapter(autos)
        recyclerView.adapter = adapter

        // Přidáme tlačítko zpět do ActionBaru
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Nastavíme listener pro tlačítko zpět
        actionBar?.setHomeAsUpIndicator(com.google.android.material.R.drawable.ic_arrow_back_black_24) // Zde můžete změnit na svou ikonu
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Zde můžete přidat akce pro kliknutí na tlačítko zpět
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
