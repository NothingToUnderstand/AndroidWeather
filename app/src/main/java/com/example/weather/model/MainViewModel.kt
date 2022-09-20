package com.example.weather.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.PerHour
import com.example.weather.data.WeatherModel

class MainViewModel :ViewModel(){
    val liveData=MutableLiveData<WeatherModel>()
    val liveDataList=MutableLiveData<List<PerHour>>()
}