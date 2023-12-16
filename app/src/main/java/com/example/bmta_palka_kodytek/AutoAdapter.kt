// Soubor: AutoAdapter.kt
package com.example.bmta_palka_kodytek.objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmta_palka_kodytek.R

class AutoAdapter(private val autos: List<Auto>) :
    RecyclerView.Adapter<AutoAdapter.AutoViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(auto: Auto)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class AutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewBrand: TextView = itemView.findViewById(R.id.textViewBrand)
        val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        val textViewSeats: TextView = itemView.findViewById(R.id.textViewSeats)
        val textViewConsumption: TextView = itemView.findViewById(R.id.textViewConsumption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_auto, parent, false)
        return AutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutoViewHolder, position: Int) {
        val auto = autos[position]
        holder.textViewBrand.text = auto.brand
        holder.textViewModel.text = auto.model
        holder.textViewSeats.text = auto.seats.toString()
        holder.textViewConsumption.text = auto.consumption

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(auto)
        }
    }


    override fun getItemCount(): Int {
        return autos.size
    }
}
