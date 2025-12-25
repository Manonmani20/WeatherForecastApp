package com.minda.weatherforecastapp.data

import com.google.gson.annotations.SerializedName

//Weather Response based on city

data class WeatherResponseDto(
    val cod: String,
    val message: Int,
    val cnt: Int,
    @SerializedName("list")
    val forecastList: List<ForecastDto>
)

data class ForecastDto(
    val dt: Long,
    val main: MainDto,
    val weather: List<WeatherConditionDto>,
    @SerializedName("dt_txt")
    val dt_txt: String)

data class MainDto(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)
data class WeatherConditionDto(
    val main: String,
    val description: String,
    val icon: String
)

data class CityDto(val name:String)
data class TempDto(val temperature:Double)
data class WeatherDto(val weather:List<WeatherConditionDto>)