package com.minda.weatherforecastapp.mvi

sealed class WeatherEffect {
    object NoInternet : WeatherEffect()
    object NoWeatherData : WeatherEffect()
    object CityNotFound : WeatherEffect()
    data class GenericError(val message: String) : WeatherEffect()
}
