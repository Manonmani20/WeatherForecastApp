package com.minda.weatherforecastapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minda.weatherforecastapp.data.WeatherState
import com.minda.weatherforecastapp.mvi.WeatherIntent
import com.minda.weatherforecastapp.data.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel@Inject constructor(
     private val repository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state

    /** P
    *
    * */
    fun process(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.SearchByCity -> loadWeather(intent.city)
            WeatherIntent.Refresh -> loadWeather(_state.value.city)
        }
    }



    private fun loadWeather(city: String) {
        viewModelScope.launch {
            _state.value = WeatherState(isLoading = true)

            val result = repository.getWeather(city)

            _state.value = WeatherState(
                weatherForecast = result,
                isLoading = false
            )
        }
    }
}
