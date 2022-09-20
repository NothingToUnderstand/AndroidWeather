package com.example.weather.adapters.days

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.data.PerDay
import com.example.weather.data.WeatherModel

class DaysComparator  : DiffUtil.ItemCallback<PerDay>() {
    override fun areItemsTheSame(oldItem: PerDay, newItem: PerDay): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: PerDay, newItem: PerDay): Boolean {
        return false
    }
}