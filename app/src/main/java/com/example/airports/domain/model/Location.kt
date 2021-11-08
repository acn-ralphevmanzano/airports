package com.example.airports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val aboveSeaLevel: Int,
    val latitude: Double,
    val latitudeDirection: String,
    val latitudeRadius: Double,
    val longitude: Double,
    val longitudeDirection: String,
    val longitudeRadius: Double
)