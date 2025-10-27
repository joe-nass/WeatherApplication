package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.data_sources.remote.ForecastRemoteSource
import com.example.weatherapplication.data.data_sources.remote.toDomain
import com.example.weatherapplication.domain.model.ForecastFullData
import com.example.weatherapplication.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteSource: ForecastRemoteSource
) :  ForecastRepository{
    override suspend fun getForecast(
        query: String,
    ): ForecastFullData {
        try {
         val response = forecastRemoteSource.getForecast(query)
            if(response.isSuccessful){
                response.body()?.let {
                    return it.toDomain()
                }
            }
        }catch (e: Exception){

        }
        return null
    }
}