package com.example.bmta_palka_kodytek.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta_palka_kodytek.R
import com.example.bmta_palka_kodytek.model.Car

// Adaptér pro recycler view, slouží k propojení modelu (Car) a GUI (aktivit)
class CarAdapter(private val carList: List<Car>, private val onClick: (Car) -> Unit) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    // Vnitřní třída pro reprezentaci jednoho prvku v recycler view, jeden prvek = Car(znacka, model, sedadla, spotreba)
    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandTextView: TextView = itemView.findViewById(R.id.brandTextView)
        val modelTextView: TextView = itemView.findViewById(R.id.modelTextView)
        val seatsTextView: TextView = itemView.findViewById(R.id.seatsTextView)
        val consumptionTextView: TextView = itemView.findViewById(R.id.consumptionTextView)
    }

    // vytvoří instanci CarViewHolder pro každou položku a nastaví do čeho se má promítat (item_car.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(itemView)
    }

    // naplňuje CarViewHolder daty položek z List<Car>
    @SuppressLint("SetTextI18n")    // pro potlačení "joinování stringů" v této metodě
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentCar = carList[position]
        holder.brandTextView.text = "Značka: ${currentCar.brand}"
        holder.modelTextView.text = "Model: ${currentCar.model}"
        holder.seatsTextView.text = "Počet sedadel: ${currentCar.seats}"
        holder.consumptionTextView.text = "Spotřeba: ${currentCar.consumption}l/100Km"

        holder.itemView.setOnClickListener {
            onClick.invoke(currentCar)
        }
    }

    override fun getItemCount() = carList.size
}