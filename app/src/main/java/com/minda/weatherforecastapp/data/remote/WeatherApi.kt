package com.minda.weatherforecastapp.data.remote

import com.minda.weatherforecastapp.data.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiRoutes.FORECAST)
    suspend fun getForecastApi(
        @Query("q") city:String,
        @Query("appid") apiKey:String,
        @Query("units") units:String = "metric"
    ): WeatherResponseDto
}