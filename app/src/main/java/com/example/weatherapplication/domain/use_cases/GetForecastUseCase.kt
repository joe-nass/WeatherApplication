package com.example.weatherapplication.domain.use_cases

import com.example.weatherapplication.domain.model.ForecastFullData
import com.example.weatherapplication.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) : UseCase<String, ForecastFullData> {

    override suspend fun invoke(params: String): ForecastFullData {
        return forecastRepository.getForecast(params)
    }
}