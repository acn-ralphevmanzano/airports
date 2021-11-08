package com.example.airports.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirportsResponse(
    val airportCode: String,
    val airportName: String,
    val city: City,
    val country: Country,
    val domesticAirport: Boolean,
    val eticketableAirport: Boolean,
    val internationalAirport: Boolean,
    val location: Location,
    val onlineIndicator: Boolean,
    val region: Region,
    val regionalAirport: Boolean
)