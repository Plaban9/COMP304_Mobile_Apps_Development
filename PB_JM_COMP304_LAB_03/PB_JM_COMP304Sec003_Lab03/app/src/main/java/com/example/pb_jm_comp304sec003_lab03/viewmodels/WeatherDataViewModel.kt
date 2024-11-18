package com.example.pb_jm_comp304sec003_lab03.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pb_jm_comp304sec003_lab03.data.WeatherDataRepository
import com.example.pb_jm_comp304sec003_lab03.models.CityData
import com.example.pb_jm_comp304sec003_lab03.models.WeatherData
import com.example.pb_jm_comp304sec003_lab03.roomDB.RoomCityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDataViewModel(private val repository: WeatherDataRepository) : ViewModel()
{
    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weatherData

    private val _cityList = MutableLiveData<List<CityData>>()
    val cityList: LiveData<List<CityData>> = _cityList

    private val _cityDBList = MutableLiveData<List<RoomCityData>>()
    val cityDBList: LiveData<List<RoomCityData>> = _cityDBList

    init {
        //_cityDBList.value = mutableListOf()
        fetchCityDBList()
    }

    fun fetchCityWeatherData(city: String)
    {
        viewModelScope.launch {
            try
            {
                val _data = repository.getCityWeatherData(city)
                _weatherData.value = _data
            }
            catch (e: Exception)
            {
                Log.d("Weather Data Fetch Failed", e.message.toString())
            }
        }
    }

    fun fetchCityList(city: String)
    {
        viewModelScope.launch {
            try
            {
                val _data = repository.getCityList(city)
                _cityList.value = _data
            }
            catch (e: Exception)
            {
                Log.d("City List Fetch Failed", e.message.toString())
            }
        }
    }

    fun fetchCityDBList()
    {
        viewModelScope.launch {
            try
            {
                val _data = repository.getCityFromDB()
                _cityDBList.value = _data
            }
            catch (e: Exception)
            {
                Log.d("City DB List Fetch Failed", e.message.toString())
            }
        }
    }

    fun insertCityIntoDB(cityData: RoomCityData)
    {
        viewModelScope.launch {
            try
            {
                if (!(_cityDBList.value!!.contains(cityData)))
                    repository.insertCityData(cityData)
            }
            catch (e: Exception)
            {
                Log.d("City DB Insert Failed", e.message.toString())
            }
        }
    }
}