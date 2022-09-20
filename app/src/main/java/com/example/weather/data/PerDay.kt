package com.example.weather.data

data class PerDay(
    val date: String,
    val max_temp: Double,
    val min_temp: Double,
    val avg_temp: Double,
    val max_wind_speed: Double,
    val avg_humidity: Double,
    val text: String,
    val icon: String,
    val uv: Double,
    val astro: Astro,
    val per_hour:List<PerHour>,
    val cityInfo: CityInfo
)
