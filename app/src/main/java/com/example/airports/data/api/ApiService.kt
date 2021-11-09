package com.example.airports.data.api

import com.example.airports.domain.model.Airport
import retrofit2.http.GET

interface ApiService {

    @GET("flight/refData/airport")
    suspend fun getAirports(): List<Airport>

}