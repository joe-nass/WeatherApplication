package com.example.weatherapplication.data.data_sources.remote

import com.example.weatherapplication.data.data_sources.remote.ForecastResponse
import javax.inject.Inject

class ForecastRemoteSourceImpl @Inject constructor(private val forecastApi: ForecastApiService) :
    ForecastRemoteSource {

    override suspend fun getForecast(query: String): Result<ForecastResponse> =
        runCatching {
            forecastApi.getForecast(query)
        }
}