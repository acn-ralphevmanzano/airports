package com.example.airports.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airports.R
import com.example.airports.databinding.ItemAirportBinding
import com.example.airports.domain.model.Airport
import com.example.airports.utils.extensions.setCircleImage

class AirportsAdapter :
    ListAdapter<Airport, AirportsAdapter.AirportsViewHolder>(AirportsDiffCallback()) {

    var onItemClick: ((Airport) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportsViewHolder {
        val binding = ItemAirportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AirportsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AirportsViewHolder(private val binding: ItemAirportBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            onItemClick?.let {
                binding.root.setOnClickListener {
                    it(getItem(adapterPosition))
                }
            }
        }

        fun bind(item: Airport) = with(binding) {
            tvName.text = item.airportName
            tvCountry.text = item.country.countryName
        }
    }

    class AirportsDiffCallback : DiffUtil.ItemCallback<Airport>() {
        override fun areItemsTheSame(oldItem: Airport, newItem: Airport) =
            oldItem.airportCode == newItem.airportCode

        override fun areContentsTheSame(oldItem: Airport, newItem: Airport) = oldItem == newItem

    }
}