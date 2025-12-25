package com.minda.weatherforecastapp.mvi

sealed class WeatherIntent {
    data class SearchByCity(val city: String) : WeatherIntent()
    object Refresh : WeatherIntent()
}