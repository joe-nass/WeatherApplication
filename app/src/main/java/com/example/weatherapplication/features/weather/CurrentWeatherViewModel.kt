package com.example.weatherapplication.features.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.data_sources.remote.toDomain
import com.example.weatherapplication.domain.model.UserLocation
import com.example.weatherapplication.domain.use_cases.GetForecastUseCase
import com.example.weatherapplication.domain.use_cases.GetUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase
) : ViewModel() {
    private val intent = MutableSharedFlow<ForecastContract.ForecastIntent>()
    private val _events = MutableSharedFlow<ForecastContract.ForecastEvent>()
    val events = _events.asSharedFlow()
    private val _uiState = MutableStateFlow(ForecastContract.ForecastUiState())
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow<String?>(null)

    fun sendIntent(i: ForecastContract.ForecastIntent) = viewModelScope.launch { intent.emit(i) }

    init {
        processIntents()
        listenToQuery()
    }

    private fun listenToQuery() = viewModelScope.launch {
        _query
            .filterNotNull()
            .distinctUntilChanged()
            .collectLatest { q ->
                loadForecast(q)
            }
    }

    private fun loadForecast(query: String) = viewModelScope.launch {
        val refreshing = uiState.value.isRefreshing

        if (!refreshing) {
            _uiState.update { it.copy(isLoading = true, error = null) }

        }

        getForecastUseCase(query)
            .onSuccess { forecast ->
                _uiState.update {
                    it.copy(
                        data = forecast.toDomain(),
                        query = query,
                        isLoading = false,
                        isRefreshing = false,
                        error = null
                    )
                }
                Log.d("TAG", "loadForecast: $forecast")
            }.onFailure { throwable ->
                Log.d("TAG", "loadForecast: ${throwable.message}")
                _uiState.update {
                    it.copy(
                        error = throwable.message.orEmpty(),
                        isLoading = false,
                        isRefreshing = false
                    )
                }
                _events.emit(ForecastContract.ForecastEvent.ShowMessage(throwable.message.toString()))
            }
    }

    private fun getForeCastOfCurrentUserLocation() = viewModelScope.launch {
        val isRefreshing = _uiState.value.isRefreshing

        getUserLocationUseCase(Unit)
            .onEach { location ->
                val userLocation = UserLocation(location.latitude, location.longitude)
                if(isRefreshing){
                    //to escape from distinct until change
                    loadForecast(userLocation.toString())
                }else {
                    _query.value = userLocation.toString()
                }
            }.catch {
                Log.d("TAG", "getUserLocation: ${it.message}")
                _uiState.update {
                    it.copy(error = it.toString(), isLoading = false, isRefreshing = false)
                }
                _events.emit(ForecastContract.ForecastEvent.ShowMessage(it.message.toString()))
            }.collect()
    }


    private fun processIntents() = viewModelScope.launch {
        intent.onEach { intent ->
            when (intent) {
                is ForecastContract.ForecastIntent.LoadWithUserLocation, is ForecastContract.ForecastIntent.Retry -> {
                    getForeCastOfCurrentUserLocation()
                }
                is ForecastContract.ForecastIntent.Refresh -> {
                    _uiState.update { it.copy(isRefreshing = true, error = null) }
                    getForeCastOfCurrentUserLocation()
                }
            }
        }.collect()
    }

}