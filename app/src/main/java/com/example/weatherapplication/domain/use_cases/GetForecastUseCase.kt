package com.example.weatherapplication.domain.use_cases

import com.example.weatherapplication.data.data_sources.remote.ForecastResponse
import com.example.weatherapplication.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) : UseCase<String, Result<ForecastResponse>> {

    override suspend fun invoke(params: String): Result<ForecastResponse> {
        return forecastRepository.getForecast(params)
    }
}