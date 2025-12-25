package com.minda.weatherforecastapp.data.mapper

import com.minda.weatherforecastapp.data.ForecastDto
import com.minda.weatherforecastapp.data.WeatherEntity

fun ForecastDto.toEntity(city: String): WeatherEntity {
    val condition = weather.firstOrNull()?.main ?: "Unknown"
    val icon = weather.firstOrNull()?.icon ?: "01d"

    return WeatherEntity(
        city = city,
        date = dt_txt.substring(0, 10), // yyyy-MM-dd
        temperature = main.temp,
        condition = condition,
        icon = icon
    )

}
