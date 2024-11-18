package com.example.pb_jm_comp304sec003_lab03.services

import com.example.pb_jm_comp304sec003_lab03.models.CityData
import com.example.pb_jm_comp304sec003_lab03.models.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "f2c7c09f8c3d472b9d2181802241011"

interface WeatherService
{

    @GET("current.json?key=${API_KEY}&aqi=yes")
    suspend fun getCityWeatherData(@Query("q") city: String): WeatherData

    @GET("search.json?key=${API_KEY}")
    suspend fun getCityList(@Query("q") city: String): List<CityData>
}