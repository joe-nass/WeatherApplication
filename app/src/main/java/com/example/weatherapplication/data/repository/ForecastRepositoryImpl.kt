package com.example.weatherapplication.data.repository

import com.example.weatherapplication.core.utils.IoDispatcher
import com.example.weatherapplication.data.data_sources.remote.ForecastRemoteSource
import com.example.weatherapplication.data.data_sources.remote.ForecastResponse
import com.example.weatherapplication.domain.repository.ForecastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteSource: ForecastRemoteSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ForecastRepository {

    override suspend fun getForecast(query: String): Result<ForecastResponse> =
        withContext(ioDispatcher) { forecastRemoteSource.getForecast(query) }

}