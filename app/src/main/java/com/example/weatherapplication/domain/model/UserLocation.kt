package com.example.weatherapplication.domain.model

data class UserLocation(val lat: Double, val long: Double){
    override fun toString(): String {
        return "$lat,$long"
    }
}
