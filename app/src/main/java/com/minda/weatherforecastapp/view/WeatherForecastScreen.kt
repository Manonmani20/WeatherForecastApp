package com.minda.weatherforecastapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minda.weatherforecastapp.mvi.WeatherIntent


@Composable
fun WeatherForecastScreen(    viewModel: WeatherViewModel = hiltViewModel()
){


    var city by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {


        OutlinedTextField(value=city ,
            onValueChange = {
                city = it
            },
            label = {
                Text("Enter City")
            }
            )


        Button(onClick = {
            viewModel.process(WeatherIntent.SearchByCity(city))
        }) {
            Text("Get Weather")

        }

    }
}