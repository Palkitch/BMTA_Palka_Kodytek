package com.example.bmta_palka_kodytek.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta_palka_kodytek.R
import com.example.bmta_palka_kodytek.objects.Car

class CarAdapter(private val cars: List<Car>) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(car: Car)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewBrand: TextView = itemView.findViewById(R.id.textViewBrand)
        val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        val textViewSeats: TextView = itemView.findViewById(R.id.textViewSeats)
        val textViewConsumption: TextView = itemView.findViewById(R.id.textViewConsumption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.textViewBrand.text = car.brand
        holder.textViewModel.text = car.model
        holder.textViewSeats.text = car.seats.toString()
        holder.textViewConsumption.text = car.consumption

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(car)
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }


}
