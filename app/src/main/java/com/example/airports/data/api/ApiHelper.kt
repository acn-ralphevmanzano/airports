package com.example.airports.data.api

import com.example.airports.domain.model.Airport

interface ApiHelper {
    suspend fun getAirports(): List<Airport>
}