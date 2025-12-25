package com.minda.weatherforecastapp.view

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WeatherIcon(iconCode: String,
                modifier: Modifier = Modifier
) {
    val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"

    AsyncImage(
        model = iconUrl,
        contentDescription = "Weather Icon",
        modifier = modifier.size(48.dp)
    )
}