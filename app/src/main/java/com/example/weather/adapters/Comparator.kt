package com.example.weather.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.data.PerHour
import com.example.weather.data.WeatherModel

class Comparator : DiffUtil.ItemCallback<PerHour>() {
    override fun areItemsTheSame(oldItem: PerHour, newItem: PerHour): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: PerHour, newItem: PerHour): Boolean {
        return false
    }
}