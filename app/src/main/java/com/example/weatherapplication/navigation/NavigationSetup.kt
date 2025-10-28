package com.example.weatherapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.navigation.ScreenRoutes
import com.example.weatherapplication.features.weather.ForecastRoute

@Composable
fun WeatherApp() {


    val navController = rememberNavController()

    NavHost(navController, startDestination = ScreenRoutes.Forecast){
        composable<ScreenRoutes.Forecast>{
            ForecastRoute()
        }
    }
}