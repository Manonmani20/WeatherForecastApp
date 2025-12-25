package com.minda.weatherforecastapp.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minda.weatherforecastapp.data.WeatherState
import com.minda.weatherforecastapp.mvi.WeatherIntent
import com.minda.weatherforecastapp.data.remote.WeatherRepository
import com.minda.weatherforecastapp.mvi.WeatherEffect
import com.minda.weatherforecastapp.utils.CityNotFoundException
import com.minda.weatherforecastapp.utils.NetworkUtils
import com.minda.weatherforecastapp.utils.NoDataException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
     private val repository: WeatherRepository,
     @ApplicationContext private val context: Context

) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state

    private val _effect = MutableSharedFlow<WeatherEffect>()
    val effect = _effect.asSharedFlow()

    /**
     *
     * User Intent processpr for
     * 1.SearchbyCity
     * 2.Refresh
     * */
    fun process(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.SearchByCity -> loadWeather(intent.city)
            WeatherIntent.Refresh -> loadWeather(_state.value.city)
        }
    }



    private fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                if (!NetworkUtils.isNetworkAvailable(context)) {
                    _effect.emit(WeatherEffect.NoInternet)
                }

                _state.value = WeatherState(isLoading = true)


                Log.e("loadWeather", "Load Weather VM")
                val result = repository.getWeather(city)
                Log.e("loadWeather", "result $result")

                _state.value = WeatherState(
                    weatherForecast = result,
                    isLoading = false
                )
            } catch (e: NoDataException) {
                _state.value = _state.value.copy(isLoading = false, weatherForecast = emptyList())
                _effect.emit(WeatherEffect.NoWeatherData)
            } catch (e: CityNotFoundException) {
                _state.value = _state.value.copy(isLoading = false, weatherForecast = emptyList())
                _effect.emit(WeatherEffect.CityNotFound)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, weatherForecast = emptyList())
                _effect.emit(WeatherEffect.GenericError(e.message ?: "Unknown error"))
            }
        }
    }
}
