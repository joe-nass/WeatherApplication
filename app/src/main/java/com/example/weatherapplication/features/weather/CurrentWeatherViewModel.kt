package com.example.weatherapplication.features.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.data_sources.remote.toDomain
import com.example.weatherapplication.domain.use_cases.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {
    private val intent = MutableSharedFlow<ForecastContract.ForecastIntent>()
    private val _events = MutableSharedFlow<ForecastContract.ForecastEvent>()
    val events = _events.asSharedFlow()
    private val _uiState = MutableStateFlow(ForecastContract.ForecastUiState())
    val uiState = _uiState.asStateFlow()


    fun sendIntent(i: ForecastContract.ForecastIntent) = viewModelScope.launch { intent.emit(i) }

    init {
        processIntents()
        sendIntent(ForecastContract.ForecastIntent.Load("Cairo"))
    }

    private fun loadForecast(query: String) = viewModelScope.launch {
        _uiState.update { ForecastContract.ForecastUiState(isLoading = true) }

        getForecastUseCase(query)
            .onSuccess { forecast ->
                _uiState.update {
                    ForecastContract.ForecastUiState(data = forecast.toDomain(), isLoading = false, query = query)
                }
                Log.d("TAG", "loadForecast: $forecast")
            }.onFailure { throwable ->
                Log.d("TAG", "loadForecast: ${throwable.message}")
                _uiState.update {
                    ForecastContract.ForecastUiState(error = throwable.message.toString(), isLoading = false)
                }
                _events.emit(ForecastContract.ForecastEvent.ShowMessage(throwable.message.toString()))
            }
    }


    private fun processIntents() = viewModelScope.launch {
        intent.onEach { intent ->
            when (intent) {
                is ForecastContract.ForecastIntent.Load -> {
                    loadForecast(intent.query)
                }

                is ForecastContract.ForecastIntent.Refresh -> {}
                is ForecastContract.ForecastIntent.Retry -> {}
            }
        }.collect()
    }

}