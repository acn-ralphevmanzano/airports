package com.example.airports.data.repository

import com.example.airports.data.api.ApiService
import com.example.airports.di.IoDispatcher
import com.example.airports.domain.model.Airport
import com.example.airports.domain.model.Resource
import com.example.airports.domain.repository.AirportRepo
import com.example.airports.utils.resultFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AirportRepoImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AirportRepo {
    override suspend fun getAirports(): Flow<Resource<List<Airport>>>
    = resultFlow(dispatcher) {
        apiService.getAirports()
    }
}