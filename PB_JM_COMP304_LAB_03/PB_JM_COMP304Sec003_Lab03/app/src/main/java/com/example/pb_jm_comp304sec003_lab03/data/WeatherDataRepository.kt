package com.example.pb_jm_comp304sec003_lab03.data

import android.util.Log
import com.example.pb_jm_comp304sec003_lab03.models.CityData
import com.example.pb_jm_comp304sec003_lab03.models.WeatherData
import com.example.pb_jm_comp304sec003_lab03.roomDB.CityDAO
import com.example.pb_jm_comp304sec003_lab03.roomDB.RoomCityData
import com.example.pb_jm_comp304sec003_lab03.services.RetrofitInstance

class WeatherDataRepository(private val roomCityDataDao: CityDAO) {
    private val weatherDataService = RetrofitInstance.weatherService

    suspend fun getCityWeatherData(city: String): WeatherData
    {
        val data = weatherDataService.getCityWeatherData(city)
        Log.d("Weather Data", data.toString())
        return data
    }

    suspend fun getCityList(city: String): List<CityData>
    {
        val data = weatherDataService.getCityList(city)
        Log.d("City List", data.toString())
        return data
    }

    suspend fun getCityFromDB() : List<RoomCityData> {
        return roomCityDataDao.getAll()
    }

    suspend fun insertCityData(cityData: RoomCityData) {
        roomCityDataDao.insertCity(cityData)
    }
}