package com.example.weather.data

import org.json.JSONArray
import org.json.JSONObject

class WeatherModel() {
    lateinit var cityInfo: CityInfo
    lateinit var perDay: ArrayList<PerDay>

    constructor(
        perDay: ArrayList<PerDay>
    ) : this() {
        this.perDay = perDay
    }

     fun getWeatherModel(ob: JSONObject): WeatherModel {
         cityInfo=parseCityInfo(ob)
        return WeatherModel(
            parsePerDay(ob.getJSONObject("forecast").getJSONArray("forecastday"))
        )
    }


    private fun parsePerDay(arr: JSONArray): ArrayList<PerDay> {
        val listPerDay = ArrayList<PerDay>()
        for (i in 0 until arr.length()) {
            val days = arr[i] as JSONObject
            val perday = PerDay(
                days.getString("date"),
                days.getJSONObject("day").getDouble("maxtemp_c"),
                days.getJSONObject("day").getDouble("mintemp_c"),
                days.getJSONObject("day").getDouble("avgtemp_c"),
                days.getJSONObject("day").getDouble("maxwind_kph"),
                days.getJSONObject("day").getDouble("avghumidity"),
                days.getJSONObject("day").getJSONObject("condition").getString("text"),
                days.getJSONObject("day").getJSONObject("condition").getString("icon"),
                days.getJSONObject("day").getDouble("uv"),
                getAstro(days),
                getPerHour(days.getJSONArray("hour")),
                cityInfo
            )
            listPerDay.add(perday)
        }
        return listPerDay
    }


    private fun getPerHour(arr: JSONArray): ArrayList<PerHour> {
        val listPerHour = ArrayList<PerHour>()
        for (i in 0 until arr.length()) {
            val hours = arr[i] as JSONObject
            val perHour = PerHour(
                hours.getString("time"),
                hours.getString("temp_c"),
                hours.getJSONObject("condition").getString("text"),
                hours.getJSONObject("condition").getString("icon")
            )
            listPerHour.add(perHour)
        }
        return listPerHour
    }

    private fun getAstro(ob: JSONObject): Astro {
        return Astro(
            ob.getJSONObject("astro").getString("sunrise"),
            ob.getJSONObject("astro").getString("sunset")
        )
    }

    private fun parseCityInfo(ob: JSONObject): CityInfo {
        return CityInfo(
            ob.getJSONObject("location").getString("name"),
            ob.getJSONObject("location").getString("region"),
            ob.getJSONObject("location").getString("country")
        )
    }

    private fun bool(i: Int): Boolean {
        return i == 1
    }

    override fun toString(): String {
        return "WeatherModel(cityInfo=$cityInfo, perDay=$perDay)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherModel

        if (cityInfo != other.cityInfo) return false
        if (perDay != other.perDay) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cityInfo.hashCode()
        result = 31 * result + perDay.hashCode()
        return result
    }

}