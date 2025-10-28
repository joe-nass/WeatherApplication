package com.example.weatherapplication.domain.repository

import com.example.weatherapplication.data.data_sources.remote.ForecastResponse

interface ForecastRepository {
    /**@param [query] parameter based on which data is sent back. It could be following:
     * Latitude and Longitude (Decimal degree) e.g: q=48.8567,2.3508
     * city name e.g.: q=Paris
     * US zip e.g.: q=10001
     * UK postcode e.g: q=SW1
     * Canada postal code e.g: q=G2J
     * metar:<metar code> e.g: q=metar:EGLL
     * iata:<3 digit airport code> e.g: q=iata:DXB
     * auto:ip IP lookup e.g: q=auto:ip
     * IP address (IPv4 and IPv6 supported) e.g: q=100.0.0.1
     * By ID returned from Search API. e.g: q=id:2801268
     */
    suspend fun getForecast(query: String): Result<ForecastResponse>
}