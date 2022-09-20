package com.example.weather.adapters.days

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.PerDay
import com.example.weather.databinding.ListDaysBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class DaysViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ListDaysBinding.bind(view)
    fun bind(it: PerDay) = with(binding) {
        val region = "${it.cityInfo.country},${it.cityInfo.region}"
        country.text = region
        city.text = it.cityInfo.city

        val avgtemp = "${it.avg_temp.roundToInt()}°C"
        currentTemp.text = avgtemp
        weatherCondition.text = it.text
        currentDateMainCard.text = it.date
        val minmax = "${it.min_temp}°C/${it.max_temp}°C"
        tempMinMax.text = minmax
        Picasso.get().load("https:${it.icon}").into(iconWeather)
        val windspeed = "${it.max_wind_speed}km/h"
        windtext.text = windspeed
        val hummidity = "${it.avg_humidity}%"
        hummiditytext.text = hummidity
        uvtext.text = it.uv.toString()
        sunrisetext.text = it.astro.sunrise
        sunsettext.text = it.astro.sunset
    }
}