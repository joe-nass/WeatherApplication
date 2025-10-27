package com.example.weatherapplication.data.data_sources.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    companion object {
        private const val FORECAST_EP: String = "forecast.json"
        private const val QUERY_PARAM: String = "q"
        private const val DAYS_PARAM: String = "days"
    }

    @GET(FORECAST_EP)
    suspend fun getForecast(
        @Query(QUERY_PARAM) query: String,
        @Query(DAYS_PARAM) days: Int = 7
    ): Response<ForecastResponse>

}