package com.minda.weatherforecastapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val city: String,
    val date: String,
    val condition: String,
    val temperature: Double,
    val icon: String

)
