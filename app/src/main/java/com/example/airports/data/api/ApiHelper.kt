package com.example.airports.data.api

import com.example.airports.domain.model.Airport
import retrofit2.Response

interface ApiHelper {
    suspend fun getAirports(): Response<List<Airport>>
}