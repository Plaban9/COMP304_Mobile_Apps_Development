package com.example.pb_jm_comp304sec003_lab03.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pb_jm_comp304sec003_lab03.data.WeatherDataRepository

class ViewModelFactory(private val repository: WeatherDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherDataViewModel::class.java))
        {
            return WeatherDataViewModel(repository) as T
        }
        else
        {
            throw IllegalArgumentException("View Model Casting Exception")
        }
    }
}