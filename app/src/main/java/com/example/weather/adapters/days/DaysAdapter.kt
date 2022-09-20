package com.example.weather.adapters.days

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.R
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.data.PerDay

class DaysAdapter : ListAdapter<PerDay, DaysViewHolder>(DaysComparator()) {
    private lateinit var adapterHours: WeatherAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_days, parent, false)
        return DaysViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(getItem(position))
        adapterHours.submitList(getItem(position).per_hour)
    }

    fun setHoursAdapter(adapter: WeatherAdapter) {
        this.adapterHours = adapter
    }


}