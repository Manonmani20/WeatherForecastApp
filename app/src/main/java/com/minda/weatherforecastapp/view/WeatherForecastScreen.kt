package com.minda.weatherforecastapp.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minda.weatherforecastapp.mvi.WeatherEffect
import com.minda.weatherforecastapp.mvi.WeatherIntent


@Composable
fun WeatherForecastScreen(    viewModel: WeatherViewModel = hiltViewModel()
){

    val context = LocalContext.current

    var city by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()


    // Launch effect for network connection to indicate user when they are searching
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect: WeatherEffect ->
            when (effect) {
                WeatherEffect.NoInternet -> {
                    Toast.makeText(
                        context,
                        "No internet connection. Showing offline data.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                WeatherEffect.NoWeatherData ->
                    Toast.makeText(context, "No weather records available for this city", Toast.LENGTH_SHORT).show()

                WeatherEffect.CityNotFound ->
                    Toast.makeText(context, "City not found", Toast.LENGTH_SHORT).show()

                is WeatherEffect.GenericError ->
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value=city ,
            onValueChange = {
                city = it
            },
            label = {
                Text("Enter City")
            }
        )

        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            viewModel.process(WeatherIntent.SearchByCity(city.trim()))
        }) {
            Text("Get Weather")

        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        state.error?.let {
            Text(text = it, color = Color.Red)
        }

        LazyColumn {
            items(state.weatherForecast) { item ->
                WeatherItem(item)
            }
        }

    }
}


