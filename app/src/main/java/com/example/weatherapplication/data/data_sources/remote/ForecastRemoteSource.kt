package com.example.weatherapplication.data.data_sources.remote

import retrofit2.Response

interface ForecastRemoteSource {
    suspend fun getForecast(query: String): Response<ForecastResponse>
}