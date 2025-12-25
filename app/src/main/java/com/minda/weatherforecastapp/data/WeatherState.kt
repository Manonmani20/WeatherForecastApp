package com.minda.weatherforecastapp.data

data class WeatherState(
    var isLoading: Boolean = false,
    val city: String ="",
    val weatherForecast: List<WeatherEntity> = emptyList(),
    val error: String? = null
)
