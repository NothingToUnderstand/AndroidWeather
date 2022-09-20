package com.example.weather.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.PerHour
import com.example.weather.databinding.ListHoursBinding
import com.squareup.picasso.Picasso


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ListHoursBinding.bind(view)
    fun bind(item: PerHour) = with(binding) {
        daytime.text = item.date_time.split(" ")[1]
        condition.text = item.text
        val temp = "${item.temp}°C"
        minmaxitem.text = temp
        Picasso.get().load("https:${item.icon}").into(im)
    }
}