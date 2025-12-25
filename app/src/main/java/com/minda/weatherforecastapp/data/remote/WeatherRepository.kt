package com.minda.weatherforecastapp.data.remote

import android.util.Log
import com.minda.weatherforecastapp.BuildConfig
import com.minda.weatherforecastapp.data.local.WeatherDao
import com.minda.weatherforecastapp.data.WeatherEntity
import com.minda.weatherforecastapp.data.mapper.toEntity
import com.minda.weatherforecastapp.utils.CityNotFoundException
import com.minda.weatherforecastapp.utils.NoDataException
import java.io.IOException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
) {


    /**
     * Fetches the weather forecast for a given city.
     *
     * Steps:
     * 1. Calls the remote API to get the forecast data.
     * 2. Processes the API response to get only 3 unique daily forecasts.
     * 3. Converts the API response to local database entities.
     * 4. Updates the local database with the new forecast.
     * 5. Returns the processed forecast data.
     * 6. If any exception occurs (e.g network failure or parse issue, invalid city name entered), returns cached data from the database.
     */
    suspend fun getWeather(city: String): List<WeatherEntity> {
        return try {
            println("GET Weather VM")
            val apiKey = BuildConfig.OPENWEATHER_API_KEY

            val response = api.getForecastApi(city, apiKey = apiKey)
            println("GET Weather VM$response")
            Log.e("GET weather","Load Weather VM")

            // Handle API 404 => invalid city response handling
            if (response.cod != "200") {
                throw CityNotFoundException((response.message ?: "City not found").toString())
            }
            val data = response.forecastList
                .toList()
                .distinctBy { it.dt_txt.substring(0, 10) }
                .take(3)
                .map { it.toEntity(city) }

            dao.clear(city)
            dao.insertAll(data)

            data
        } catch (e: IOException) {
            // Network failure case
            val cached = dao.getWeather(city) //if presents in local db for this city
            if (cached.isEmpty()) throw NoDataException("No weather records available for this city")
            else cached
        } catch (e: CityNotFoundException) {
            throw e
        } catch (e: Exception) {
            // Other unknown errors fallback for generic error hanle
            val cached = dao.getWeather(city)   //if presents in local db for this city
            if (cached.isEmpty()) throw NoDataException("No weather records available for this city")
            else cached
        }
    }
}
