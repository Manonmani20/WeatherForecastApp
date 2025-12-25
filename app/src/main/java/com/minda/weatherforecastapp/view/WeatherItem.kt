package com.minda.weatherforecastapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minda.weatherforecastapp.data.WeatherEntity

@Composable
fun WeatherItem(weather: WeatherEntity) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = weather.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${weather.temperature}°C",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = weather.condition,
                    fontSize = 14.sp
                )
            }
            WeatherIcon(
                iconCode = weather.icon,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}
