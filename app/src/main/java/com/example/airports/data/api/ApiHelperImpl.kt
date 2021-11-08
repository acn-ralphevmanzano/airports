package com.example.airports.data.api

import com.example.airports.domain.model.AirportsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getAirports() = apiService.getAirports()
}