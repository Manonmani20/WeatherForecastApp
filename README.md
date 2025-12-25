# WeatherForecastApp

A simple Android app to display a 3-day weather forecast for any city.

## Features
- Fetch weather data using the OpenWeatherMap API
- Offline access using Room database
- Built with Jetpack Compose, Hilt, and Kotlin
- Handles error cases:
  - City not found
  - No weather data
  - No internet connection
  - Generic API errors

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/Manonmani20/WeatherForecastApp.git

```
2. Open in Android Studio
3. Add your OpenWeatherMap API key in local.properties:
     OPENWEATHER_API_KEY= your_api_key_here
4.Build and run the app
