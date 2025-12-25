package com.minda.weatherforecastapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minda.weatherforecastapp.data.WeatherEntity

@Dao
interface WeatherDao1 {

    @Query("SELECT * FROM weather WHERE city = :city")
    suspend fun getWeather(city: String): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<WeatherEntity>)

    @Query("DELETE FROM weather WHERE city = :city")
    suspend fun clear(city: String)

}

@Dao
interface WeatherDao{
    @Query("SELECT * FROM weather WHERE city =:city")
    suspend fun getWeather(city: String):List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items:List<WeatherEntity>)

    @Query("DELETE FROM weather WHERE city =:city")
    suspend fun clear(city: String)

    @Query("DELETE FROM weather")
    suspend fun clearAll()
}