package com.example.airports.data.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getAirports() = apiService.getAirports()
}