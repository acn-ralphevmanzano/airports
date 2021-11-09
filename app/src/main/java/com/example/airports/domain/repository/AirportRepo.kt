package com.example.airports.domain.repository

import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AirportRepo {
    suspend fun getAirports(): Flow<Resource<List<Airport>>>
}