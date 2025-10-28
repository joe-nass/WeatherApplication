package com.example.weatherapplication.data.data_sources.remote

import com.example.weatherapplication.data.data_sources.remote.ForecastResponse


interface ForecastRemoteSource {
    suspend fun getForecast(query: String): Result<ForecastResponse>
}