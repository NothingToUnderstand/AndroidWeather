package com.example.weather

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.adapters.days.DaysAdapter
import com.example.weather.data.PerDay
import com.example.weather.data.WeatherModel
import com.example.weather.listener.MyScrollListener
import com.example.weather.model.MainViewModel
import org.json.JSONObject

const val KEY = "9870986f35a84866997143956220809"

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var adapterDays: DaysAdapter
    private lateinit var adapterHours: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chekPermission()
        setContentView(R.layout.activity_main)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.placeholder,MainFragment.newInstance())
//            .commit()
        getWeatherData("Kiev")
        initRcView()
        model.liveData.observe(this) {
            adapterDays.submitList(it.perDay)
            adapterDays.setHoursAdapter(adapterHours)
        }
    }

    private fun getWeatherData(cityName: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json?" +
                "key=$KEY" +
                "&q=$cityName" +
                "&days=5" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { result ->
            parseData(result)
        }, { er ->
            Log.d("MyLog", "Error: $er")
        })
        queue.add(req)
    }

    private fun parseData(res: String) {
        val item = WeatherModel().getWeatherModel(JSONObject(res))
        model.liveData.value = item
    }

    private fun initRcView() {
        val rc: RecyclerView = findViewById(R.id.rc)
        val rchours:RecyclerView=findViewById(R.id.rchours)
        rc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterDays = DaysAdapter()
        rc.adapter = adapterDays
        rchours.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterHours= WeatherAdapter()
        rchours.adapter=adapterHours
        rc.addOnScrollListener(MyScrollListener())
        rc.setItemViewCacheSize(0)
        rchours.setItemViewCacheSize(0)
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(this, "Permission is $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chekPermission() {
        val per: String = Manifest.permission.ACCESS_FINE_LOCATION
        if (!isPermissionGranted(per)) {
            permissionListener()
            pLauncher.launch(per)
        }
    }

    private fun isPermissionGranted(p: String): Boolean {

        return ContextCompat.checkSelfPermission(
            this as AppCompatActivity, p
        ) == 0
    }
}