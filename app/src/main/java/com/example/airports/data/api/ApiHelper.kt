package com.example.airports.data.api

import com.example.airports.domain.model.AirportsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiHelper {
    suspend fun getAirports(): AirportsResponse
}