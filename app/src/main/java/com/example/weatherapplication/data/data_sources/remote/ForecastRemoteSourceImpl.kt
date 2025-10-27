package com.example.weatherapplication.data.data_sources.remote

import retrofit2.Response
import javax.inject.Inject

class ForecastRemoteSourceImpl @Inject constructor(private val forecastApi: ForecastApiService) :
    ForecastRemoteSource {
    override suspend fun getForecast(
        query: String,
        days: Int
    ): Response<ForecastResponse> = forecastApi.getForecast(query, days)

}