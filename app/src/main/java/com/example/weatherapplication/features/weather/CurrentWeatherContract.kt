package com.example.weatherapplication.features.weather

import com.example.weatherapplication.domain.model.ForecastFullData


interface ForecastContract {
    sealed interface ForecastIntent {
        data object LoadWithUserLocation : ForecastIntent
        data object Refresh : ForecastIntent
        data object Retry : ForecastIntent
    }

    data class ForecastUiState(
        val isLoading: Boolean = false,
        val query: String = "",
        val data: ForecastFullData? = null,
        val isRefreshing: Boolean = false,
        val error: String? = null,
    )

    sealed interface ForecastEvent {
        data class ShowMessage(val message: String) : ForecastEvent
        object NavigateToSearch : ForecastEvent
    }

}